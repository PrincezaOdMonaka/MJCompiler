package rs.ac.bg.etf.pp1;

import com.sun.istack.internal.logging.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticPass extends VisitorAdaptor {
	
	Logger log = Logger.getLogger(getClass());
	int varDeclCount = 0;
	Obj method = null;
	int printCallCount = 0;
	
	public int getPrintCallCount() {
		return printCallCount;
	}
	
	public void visit(VarDeclList varDeclList) {
		
	}
	
	public SemanticPass() {
        Tab.insert(Obj.Type, "bool", new Struct(Struct.Bool));
	}
	
    public void visit(Type type){
        Obj typeObj = Tab.find(type.getTypeName());
        if(typeObj == Tab.noObj){
            log.info("Cannot find type: " + type.getTypeName() + type);
        }
        else{
            if(typeObj.getKind() != Obj.Type){
            	log.info("Type name " + type.getTypeName() + " is not a type." + type);
            }
            else{
                type.struct = typeObj.getType();
            }
        }
    }
	
	public void visit(VarDecl varDecl) {
		VarDeclList declList;
		SyntaxNode parent;
		for(parent = varDecl.getParent(); 
				!(parent instanceof VarDeclList); 
				parent = parent.getParent());

		declList = (VarDeclList) parent;
		Obj varObj = Tab.insert(Obj.Var, varDecl.getVarName(), declList.getType().struct);
	}
	
	public void visit(VarDeclArr varDeclArr) {
		VarDeclList declList;
		SyntaxNode parent;
		for(parent = varDeclArr.getParent(); 
				!(parent instanceof VarDeclList); 
				parent = parent.getParent());

		declList = (VarDeclList) parent;
		Struct arrayType = new Struct(Struct.Array, declList.getType().struct);
		Obj varObj = Tab.insert(Obj.Var, varDeclArr.getVarName(), arrayType);
	}
	
	public void visit(ConstDeclarationChar constChar) {
		ConstDeclrChar declList;
		SyntaxNode parent;
		for(parent = constChar.getParent(); 
				!(parent instanceof ConstDeclrChar); 
				parent = parent.getParent());

		declList = (ConstDeclrChar) parent;
		Obj constObj = Tab.insert(Obj.Con, constChar.getCName(), 
				declList.getType().struct);
	}
	
	public void visit(ConstDeclarationBool constBool) {
		ConstDeclrBool declList;
		SyntaxNode parent;
		for(parent = constBool.getParent(); 
				!(parent instanceof ConstDeclrBool); 
				parent = parent.getParent());

		declList = (ConstDeclrBool) parent;
		Obj constObj = Tab.insert(Obj.Con, constBool.getCName(), 
				declList.getType().struct);
		
		constObj.setAdr(constBool.getCVal()?1:0);
	}
	
	public void visit(ConstDeclarationNum constNum) {
		ConstDeclrNum declList;
		SyntaxNode parent;
		for(parent = constNum.getParent(); 
				!(parent instanceof ConstDeclrNum); 
				parent = parent.getParent());

		declList = (ConstDeclrNum) parent;
		Obj constObj = Tab.insert(Obj.Con, constNum.getCName(), 
				declList.getType().struct);
		
		constObj.setAdr(constNum.getCVal());
	}
	
	public void visit(TypeMethodTypeName methodTypeName) {
		methodTypeName.obj = method = Tab.insert(Obj.Meth, methodTypeName.getMethName(), 
				methodTypeName.getType().struct);
		Tab.openScope();
		method.setLevel(0);
	}
	
	public void visit(VoidMethodTypeName methodTypeName) {
		methodTypeName.obj = method = Tab.insert(Obj.Meth, methodTypeName.getMethName(), 
				Tab.noType);
		Tab.openScope();
		method.setLevel(0);
	}
	
	
	public void visit(MethodDeclaration methodDecl) {
    	Tab.chainLocalSymbols(methodDecl.getMethodTypeName().obj);
    	log.info(methodDecl.getMethodTypeName().obj.toString());
    	Tab.closeScope();
    	
    	method = null;
	}
	
	public void visit(FormalParamVar formalParamVar) {
		Obj varObj = Tab.insert(Obj.Var, formalParamVar.getParamId(), 
				formalParamVar.getType().struct);
		varObj.setFpPos(Tab.currentScope.getLocals().symbols().size()-1);
	}
	
	public void visit(FormalParamArr formalParamArr) {
		Struct arrayType = new Struct(Struct.Array, 
				formalParamArr.getType().struct);
		Obj varObj = Tab.insert(Obj.Var, formalParamArr.getParamId(), 
				arrayType);
		varObj.setFpPos(Tab.currentScope.getLocals().symbols().size()-1);
	}
		
    public void visit(ProgName progName) { 
    	progName.obj = Tab.insert(Obj.Prog, progName.getPName(), Tab.noType);
    	Tab.openScope();
    }
    
    public void visit(Program program) { 
    	Tab.chainLocalSymbols(program.getProgName().obj);
    	Tab.closeScope();
    }
    
}
