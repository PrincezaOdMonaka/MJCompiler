package rs.ac.bg.etf.pp1;

import java.util.Stack;

import com.sun.istack.internal.logging.Logger;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {

	int mainPc;
	int varDataSize;
	int boolFalse = 0;
	int boolTrue = 1;
	
	Logger log = Logger.getLogger(getClass());
	
	Stack<Integer> ifAddrStack = new Stack<>();
	Stack<Integer> elseAddrStack = new Stack<>();
	Stack<Integer> ifEndAddrStack = new Stack<>();
	int getMainPc(){
		return mainPc;
	}
	
	public void visit(PrintStmt print) {
		if(print.getExpr().struct == Tab.intType) {
			Code.loadConst(4);
			Code.put(Code.print);
		} else {
            Code.loadConst(1);
            Code.put(Code.bprint);
		}
	}

    public void visit(FactorNum factorNum){
        Code.loadConst(factorNum.getNumberFactor());
    }

    public void visit(FactorBool factorBool){
        if(factorBool.getBoolFactor())
            Code.loadConst(1);
        else
            Code.loadConst(0);
    }

    public void visit(FactorChar factorChar){
        Code.loadConst(factorChar.getCharFactor());
    }
    
    public void visit(FactorDesignator factorDesignator){
        Code.load(factorDesignator.getDesignator().obj);
    }
    
	public void visit(FactorFuncCall funcCall){
		Obj functionObj = funcCall.getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
	}
	
	public void visit(DesignatorStatementFcall funcCall){
		Obj functionObj = funcCall.getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);

	}
	
	public void visit(IndexMid midIndex) {
		Code.load(((DesignatorIndex)(midIndex.getParent())).getDesignator().obj);
	}
	
    public void visit(FactorNewArray factorNewArray){
        Code.put(Code.newarray);
        Code.put(factorNewArray.getType().struct == Tab.charType ? 0 : 1);
    }
	
    public void visit(TermList termList){
        if(termList.getMulop() instanceof Mulopr)
            Code.put(Code.mul);
        else if(termList.getMulop() instanceof Divopr)
            Code.put(Code.div);
        else if(termList.getMulop() instanceof Modopr)
            Code.put(Code.rem);
    }
    
    public void visit(ExprList list){
        if(list.getAddop() instanceof Addopr)
            Code.put(Code.add);
        else
            Code.put(Code.sub);
    }
    
    public void visit(DesignatorStatementAssign designatorAssign){
        Code.store(designatorAssign.getDesignator().obj);
    }
    
    public void visit(DesignatorBase designatorBase){
        if(designatorBase.obj.getKind() == Obj.Fld || 
        		(designatorBase.obj.getKind() == Obj.Meth && designatorBase.obj.getFpPos() == 1))
            Code.load(designatorBase.obj);
    }    
    
	public void visit(VoidMethodTypeName methodTypeName){
		
		if("main".equalsIgnoreCase(methodTypeName.getMethName())){
			mainPc = Code.pc;
		}
		methodTypeName.obj.setAdr(Code.pc);
		// Collect arguments and local variables
		SyntaxNode methodNode = methodTypeName.getParent();
	
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		
		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);
		
		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());
	
	}
	
	public void visit(TypeMethodTypeName methodTypeName){
		methodTypeName.obj.setAdr(Code.pc);
		// Collect arguments and local variables
		SyntaxNode methodNode = methodTypeName.getParent();
	
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		
		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);
		
		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());
	
	}
	public void visit(MethodDeclaration methodDecl){
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
    public void visit(ReadStatement readStatement){
        Obj obj = readStatement.getDesignator().obj;
        if(obj.getType().getKind() == Struct.Char)
            Code.put(Code.bread);
        else
            Code.put(Code.read);

        Code.store(obj);
    }
    
    // Conditions
    
    //ifAddrStack
    
    int getRelOpCode(Relop relOp) {
        if (relOp instanceof RelopEq) return Code.eq;
        if (relOp instanceof RelopNeq) return Code.ne;
        if (relOp instanceof RelopGt) return Code.gt;
        if (relOp instanceof RelopGteq) return Code.ge;
        if (relOp instanceof RelopLt) return Code.lt;
        return Code.le;
    }
    
    public void visit(CondFactRelop condFact) {
    	int relOpCode = getRelOpCode(condFact.getRelop());
    	
    	Code.put(Code.jcc + relOpCode);
        Code.put2(7);
        Code.loadConst(0);    
        Code.put(Code.jmp);
        Code.put2(4);
        Code.loadConst(1);
    }
    
    public void visit(CondBaseTerm condFact) {
    	// nothing, condFact value on e stack
    }
    
    public void visit(CondBaseStatement condBaseStmt) {
    	// nothing, condFact value on e stack
    }
    
    public void visit(ConditionStatement condStatement) {
    	int pc = Code.pc;
    	
        int address= 0;
        
        int elseRet = elseAddrStack.pop();
        if(elseRet != -1) {
        	address = ifAddrStack.pop();
        	Code.put2(address, elseRet - address + 1);
        }
        
        int ifend = ifEndAddrStack.pop();
        Code.put2(ifend, pc - ifend + 1);
        
        while(!ifAddrStack.isEmpty()) {
        	address = ifAddrStack.pop();
            Code.put2(address, pc - address + 1);
        }
    }

    
    public void visit(IfStatement ifStatement) {
    	Code.loadConst(1);
        Code.put(Code.jcc + Code.eq);
        Code.put2(6);
        Code.put(Code.jmp);
        ifAddrStack.push(Code.pc);
        Code.put2(0);
    }

    public void visit(NoElseStmt noElse) {
    	log.info("noelse");
    	elseAddrStack.push(-1);
    }
    
    public void visit(ElseBegin elseBegin) {
    	log.info("elsebg");
    	elseAddrStack.push(Code.pc);
    }
    
    public void visit (IfEnd ifEnd ) {
    	Code.put(Code.jmp);
        ifEndAddrStack.push(Code.pc);
        Code.put2(0);
    }
    
    public void visit(ConditionTerm condTerm ) {
    	// top && top-1 of estack
    	Code.put(Code.mul);
    }
    
}
