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
	boolean forConds = false;
	
	Logger log = Logger.getLogger(getClass());
	
	Stack<Integer> ifBlockEndAddrStack = new Stack<>();
	Stack<Integer> elseAddrStack = new Stack<>();
	Stack<Integer> ifEndAddrStack = new Stack<>();
	Stack<Integer> ifBeginAddrStack = new Stack<>();
	Stack<Integer> nextOrAddrStack = new Stack<>();
	
	Stack<Integer> forTop = new Stack<>(); // condition calc begin
	Stack<Integer> forBottom = new Stack<>();
	Stack<Integer> getForBottom = new Stack<>(); // break & cond check jump
	Stack<Integer> loopOpAddr = new Stack<>(); // where to jump for new iteration
	Stack<Integer> forCondJump = new Stack<>();
	
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
    
    //ifBlockEndAddrStack
    
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
    
    // and
    public void visit(CondBaseTerm condFact) {
	    	// condFact value on e stack
    		Code.loadConst(0);
            Code.put(Code.jcc + Code.eq);
    	        Code.put2(7);
    	     // true
    	     Code.loadConst(1);
    	     Code.put(Code.jmp);
    	     Code.put2(7);   
    	     //false                                                                         
    	     Code.loadConst(0);
    	     Code.put(Code.jmp); 
    	     nextOrAddrStack.push(Code.pc);
            Code.put2(0);

    }
    
    
    // or
    public void visit(CondBaseStatement condBaseStmt) {
		int pc = Code.pc;
    	if(!nextOrAddrStack.empty()) {
        	int addr = nextOrAddrStack.pop();
        	Code.put2(addr, pc - addr + 1);
    	}
    	
        Code.loadConst(0);
        Code.put(Code.jcc + Code.gt);
	        Code.put2(7);
	     // false
	     Code.loadConst(0);
	     Code.put(Code.jmp);
	     Code.put2(7);   
	     //true                                                                             
	     Code.loadConst(1);
	     Code.put(Code.jmp); 
        ifBeginAddrStack.push(Code.pc);
        Code.put2(0);
        Code.loadConst(0);
    }
    
    public void visit(CondStatementWrapper condStmtWrapper) {
    	ifBlockEndAddrStack.push(-1);
    }
    
    public void visit(ConditionStatement condStatement) {
    	int pc = Code.pc;
    	
        int address= 0;
        
        int elseRet = elseAddrStack.pop();
        if(elseRet != -1) {
        	address = ifBlockEndAddrStack.pop();
        	Code.put2(address, elseRet - address + 1);
        }
        
    	if(!nextOrAddrStack.empty()) {
        	int addr = nextOrAddrStack.pop();
        	Code.put2(addr, pc - addr + 1);
    	}
        
        int ifend = ifEndAddrStack.pop();
        Code.put2(ifend, pc - ifend + 1);
        
        
        while(!nextOrAddrStack.empty()) {
        	address = nextOrAddrStack.pop();
            Code.put2(address, pc - address + 1);
        }
        
        
        while(!ifBlockEndAddrStack.isEmpty()) {
        	address = ifBlockEndAddrStack.pop();
        	if(address==-1) break;
            Code.put2(address, pc - address + 1);
        }
    }

    
    public void visit(IfStatement ifStatement) {
    	Code.loadConst(1);
        Code.put(Code.jcc + Code.eq);
        Code.put2(6);
        Code.put(Code.jmp);
        ifBlockEndAddrStack.push(Code.pc);
        Code.put2(0);
        
        int pc = Code.pc;
        while(!ifBeginAddrStack.empty()) {
        	int addr = ifBeginAddrStack.pop();
        	Code.put2(addr, pc - addr + 1);
        	
        }
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
    
	public void visit(ConditionStmt condStmt) {
    	int pc = Code.pc;
    	if(!nextOrAddrStack.empty()) {
        	int addr = nextOrAddrStack.pop();
        	Code.put2(addr, pc - addr + 1);
    	}
    	
       Code.put(Code.add);
       Code.loadConst(0);
       Code.put(Code.jcc + Code.gt); // if true fall into if statement immediately
       ifBeginAddrStack.push(Code.pc);
       Code.put2(0);

       Code.loadConst(0); // leave false on stack
    }
	
	public void visit(NoForCond noForCond) {
		Code.loadConst(1);
		Code.put(Code.jmp);
		forCondJump.push(Code.pc);
		Code.put2(0);
    	
		loopOpAddr.push(Code.pc);
	}
	
	public void visit(ForCond forCond) {
		
		forConds = false;
		// for continue
		
		// optimized and and or true jump address
        
        int pc = Code.pc; // loop condition jump check address
        while(!ifBeginAddrStack.empty()) {
        	int addr = ifBeginAddrStack.pop();
        	Code.put2(addr, pc - addr + 1);
        	
        }
        
    	// cleanup for last and
    	while(!nextOrAddrStack.empty()) {
        	int addr = nextOrAddrStack.pop();
        	Code.put2(addr, pc - addr + 1);
    	}
    	
		Code.put(Code.jmp);
		forCondJump.push(Code.pc);
		Code.put2(0);
    	
		loopOpAddr.push(Code.pc);
	}
	
	public void visit(ForCondBegin begin) {
		forTop.push(Code.pc);
		getForBottom.push(-1);
		forConds = true;
	}
	
	public void visit(ForJump forJump) {
		while(!forCondJump.empty()) {
			int addr = forCondJump.pop();
			Code.put2(addr, Code.pc - addr + 1);
		}
		
       	Code.loadConst(0);
        Code.put(Code.jcc + Code.eq);
        getForBottom.push(Code.pc);
        Code.put2(0);
	}
	
	public void visit(ForLoopOp loopOp) {
		Code.put(Code.jmp);
		int adr = forTop.peek();
		Code.put2(adr - Code.pc + 1); // beginning of condition calculation
	}
	
	public void visit(BreakStatement breakStmt) {
		Code.put(Code.jmp);
		getForBottom.push(Code.pc);
		Code.put2(0);
	}
	
	public void visit(ContinueStatement continueStmt) {
		Code.put(Code.jmp);
		Code.put2(loopOpAddr.peek() - Code.pc + 1);
	}
	
	public void visit(ForStmtOne forStmt) {
		Code.put(Code.jmp);
		Code.put2(loopOpAddr.peek() - Code.pc + 1); // jump to loop calculation

		while(!getForBottom.empty()) {
			int addr = getForBottom.pop();
			if(addr==-1) break;
			Code.put2(addr, Code.pc - addr + 1);
		}
		
		loopOpAddr.pop();
		forTop.pop();
	}
	
	public void visit(DesignatorStmtPostinc designator) {
        designator.getDesignator().getDesignatorSpec().traverseBottomUp(this);
		Code.load(designator.getDesignator().getDesignatorSpec().obj);
        Code.loadConst(1);
        Code.put(Code.add);
		Code.store(designator.getDesignator().getDesignatorSpec().obj);
	}
	
	public void visit(DesignatorStmtPostdec designator) {
        designator.getDesignator().getDesignatorSpec().traverseBottomUp(this);
		Code.load(designator.getDesignator().getDesignatorSpec().obj);
        Code.loadConst(1);
        Code.put(Code.sub);
		Code.store(designator.getDesignator().getDesignatorSpec().obj);
	}

}

