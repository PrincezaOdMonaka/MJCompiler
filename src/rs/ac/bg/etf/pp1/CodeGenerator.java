package rs.ac.bg.etf.pp1;

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
	Logger log = Logger.getLogger(getClass());

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
    	log.info("factornum"+factorNum.getNumberFactor());
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
		log.info("index"+((DesignatorIndex)(midIndex.getParent())).getDesignator().obj.getKind()
				+((DesignatorIndex)(midIndex.getParent())).getDesignator().obj.getType().getKind());
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
    
    public void visit(DesignatorStatementAssign designatorAssign){
        Code.store(designatorAssign.getDesignator().obj);
    }
    
    public void visit(DesignatorBase designatorBase){
    	log.info("base");
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
}
