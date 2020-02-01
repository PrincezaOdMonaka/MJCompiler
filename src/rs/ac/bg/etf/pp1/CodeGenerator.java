package rs.ac.bg.etf.pp1;

import com.sun.istack.internal.logging.Logger;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;

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
    
    public void visit(TermList termList){
        if(termList.getMulop() instanceof Mulopr)
            Code.put(Code.mul);
        else if(termList.getMulop() instanceof Divopr)
            Code.put(Code.div);
        else if(termList.getMulop() instanceof Modopr)
            Code.put(Code.rem);
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
	
	public void visit(MethodDeclaration methodDecl){
		log.info("Methoddecl");
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
}
