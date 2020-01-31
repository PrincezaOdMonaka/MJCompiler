package rs.ac.bg.etf.pp1;

import com.sun.istack.internal.logging.Logger;
import com.sun.java_cup.internal.runtime.Symbol;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticPass extends VisitorAdaptor {
	
	Logger log = Logger.getLogger(getClass());
	int varDeclCount = 0;
	Obj method = null;
	Obj classDeclaration = null;
	int printCallCount = 0;
	
	public int getPrintCallCount() {
		return printCallCount;
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
//	TODO: argumenti, da li postoji simbol, scope 
	public void visit(VarDecl varDecl) {
		VarDeclList declList;
		SyntaxNode parent;
		if(findCurrentScope(varDecl.getVarName())!=null) {
			log.info("Symbol with name " + varDecl.getVarName() + " already exists.");
			return;
		}
		for(parent = varDecl.getParent(); 
				!(parent instanceof VarDeclList); 
				parent = parent.getParent());

		declList = (VarDeclList) parent;

		Obj var;
		if(method == null) {
			var = Tab.insert(Obj.Fld, varDecl.getVarName(), declList.getType().struct);
		}
		else {
			var = Tab.insert(Obj.Var, varDecl.getVarName(), declList.getType().struct);
		}
		var.setFpPos(-1);
	}
	
	public void visit(VarDeclArr varDeclArr) {
		VarDeclList declList;
		SyntaxNode parent;
		for(parent = varDeclArr.getParent(); 
				!(parent instanceof VarDeclList); 
				parent = parent.getParent());

		declList = (VarDeclList) parent;
		Struct arrayType = new Struct(Struct.Array, declList.getType().struct);
		if(method == null) {
			Tab.insert(Obj.Fld, varDeclArr.getVarName(), arrayType);
		}
		else {
			Tab.insert(Obj.Var, varDeclArr.getVarName(), arrayType);
		}
	}
	
	public void visit(ConstDeclarationChar constChar) {
		ConstDeclrChar declList;
		SyntaxNode parent;
		if(findCurrentScope(constChar.getCName())!=null) {
			log.info("Symbol with name " + constChar.getCName() + " already exists.");
			return;
		}
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
		if(findCurrentScope(constBool.getCName())!=null) {
			log.info("Symbol with name " + constBool.getCName() + " already exists.");
			return;
		}
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
		if(findCurrentScope(constNum.getCName())!=null) {
			log.info("Symbol with name " + constNum.getCName() + " already exists.");
			return;
		}
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
		if(classDeclaration != null) {
			Obj varObj = Tab.insert(Obj.Var, "this",
					classDeclaration.getType());
			varObj.setFpPos(Tab.currentScope.getLocals().symbols().size()-1);
		}
	}
	
	public void visit(VoidMethodTypeName methodTypeName) {
		methodTypeName.obj = method = Tab.insert(Obj.Meth, methodTypeName.getMethName(), 
				Tab.noType);
		Tab.openScope();
		if(classDeclaration != null) {
			Obj varObj = Tab.insert(Obj.Var, "this",
					classDeclaration.getType());
			varObj.setFpPos(Tab.currentScope.getLocals().symbols().size()-1);
		}
		method.setLevel(0);
	}
	
	
	public void visit(MethodDeclaration methodDecl) {
		methodDecl.obj = methodDecl.getMethodTypeName().obj;
    	Tab.chainLocalSymbols(methodDecl.getMethodTypeName().obj);
    	Tab.closeScope();
    	if(method.getName().equals("main") && 
    			(method.getType()!=Tab.noType || method.getLocalSymbols().size()!=0)) {
			log.info("Bad definition of main function.");
    	}
    	method = null;
	}
	
	public void visit(AbsMethodDecl methodDecl) {
    	Tab.chainLocalSymbols(methodDecl.getMethodTypeName().obj);
    	Tab.closeScope();
    	
    	method = null;
	}
	
	public void visit(FormalParamVar formalParamVar) {
		if(findCurrentScope(formalParamVar.getParamId())!=null) {
			log.info("Symbol with name " + formalParamVar.getParamId() + " already exists.");
			return;
		}
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
	
	public void visit(ClassName className) {
        className.obj = classDeclaration = Tab.insert(Obj.Type, className.getCName(), 
        		new Struct(Struct.Class));
        Tab.openScope();
	}
	
	public void visit(ClassDeclaration classDecl) {
		Tab.chainLocalSymbols(classDeclaration.getType());
		Tab.closeScope();
		
		classDeclaration = null;
	}
	
	public void visit(AbsClassName className) {
        className.obj = classDeclaration = Tab.insert(Obj.Type, className.getCName(), 
        		new Struct(Struct.Class));
        Tab.openScope();
	}
	
	public void visit(AbsClass classDecl) {
		Tab.chainLocalSymbols(classDeclaration.getType());
		Tab.closeScope();
		
		classDeclaration = null;
	}
	
	public void visit(DesignatorBase base) {
		base.obj = findVisibleSymbol(base.getName());
//		log.info("Designator base" + base.obj + base.getName());
	}
	
	public void visit(DesignatorMember member) {
		member.obj = findVisibleSymbol(member.getName());
	}
	
	public void visit(DesignatorIndex arrayIndex) {
        Obj parent = arrayIndex.getDesignator().obj;
        arrayIndex.obj = new Obj(Obj.Elem, "", parent.getType().getElemType());
	}
	
	public void visit(ActualsPars actualParams) {
		Obj method = null;
		for(SyntaxNode parent = actualParams.getParent();;
				parent = parent.getParent()
				) {
			if(parent instanceof DesignatorStatementFcall) {
				log.info("DSF"+((DesignatorBase)((DesignatorStatementFcall)parent).getDesignator().getDesignatorSpec()).getName());
				method = lookupSymbolInScope(((DesignatorBase)((DesignatorStatementFcall)parent).getDesignator().getDesignatorSpec()).getName());	
				break;
			}
			else if(parent instanceof FactorFuncCall) {
				log.info("FFC"+((DesignatorBase)((FactorFuncCall)parent).getDesignator().getDesignatorSpec()).getName());
				method = lookupSymbolInScope(((DesignatorBase)((FactorFuncCall)parent).getDesignator().getDesignatorSpec()).getName());	
				break;
			}
		}
		
		log.info("METHOD "+(method!=null?method.getName():"null"));
		

		if(method.getKind()==Obj.Meth) {
			ActualParams params = null; ActualParam param = null;
			if(actualParams.getActualParamList() instanceof ActualParams) {
				params = (ActualParams)actualParams.getActualParamList();
			}
			else {
				param = (ActualParam)actualParams.getActualParamList();
			}
			Obj arg;
			Object[] locals = method.getLocalSymbols().toArray();
			for(int i=locals.length - 1; i >= 0; i--) {
				if((arg=(Obj)(locals[i])).getFpPos()==-1) continue;
				
				if(params!=null){
					if(compatibleTypes(arg.getType(), params.getExpr().struct)) {
						log.info("paramsok");
					} else 
						log.info("paramsnotok");
					
					if(params.getActualParamList() instanceof ActualParams) {
						params = (ActualParams)(params.getActualParamList());
					}
					else {
						param = (ActualParam)(params.getActualParamList());
						params = null;
					}
				}
				else {
					if(compatibleTypes(arg.getType(), param.getExpr().struct)) {
						log.info("param1ok");
					} else log.info("param1notok");
				}
				
			}
		}
	}
	
	boolean compatibleTypes(Struct lhs, Struct rhs) {
		if(lhs==rhs ||
				(lhs.getKind() == Struct.Array 
				&& lhs.getKind() == Struct.Array
				&& compatibleTypes(lhs.getElemType(), rhs.getElemType()))
			)
			return true;
		return false;
	}
	
	public void visit(ExprTerm expr) {
		log.info("exprterm"+ expr.getTerm().struct.getKind());
		expr.struct = expr.getTerm().struct;
	}

	public void visit(ExprList expr) {
		expr.struct = expr.getTerm().struct;
	}
	
	public void visit(ExprNegatedTerm expr) {
		expr.struct = expr.getTerm().struct;
		if(expr.struct != Tab.intType)
		{//report error
		}
	}
	
	public void visit(TermBase term) {
		// factor struct null
		log.info(term.toString(" "));
		log.info("term"+ term.getFactor().struct.getKind());
		term.struct = term.getFactor().struct;
	}
	
	 public void visit(FactorNum factorNum){
	        factorNum.struct = Tab.intType;
	    }

    public void visit(FactorBool factorBool){
        factorBool.struct = Tab.find("bool").getType();
    }

    public void visit(FactorChar factorChar){
        factorChar.struct = Tab.charType;
    }
    //nije ok
    public void visit(FactorDesignator designator){
        designator.struct = designator.getDesignator().getDesignatorSpec().obj.getType();
    }

    public void visit(FactorFuncCall factor){
    	String name = "";
    	if(factor.getDesignator().getDesignatorSpec() 
    			instanceof DesignatorBase)
    		name = ((DesignatorBase)factor.getDesignator().getDesignatorSpec()).getName();
    	else if (factor.getDesignator().getDesignatorSpec() 
    			instanceof DesignatorIndex) {
    		name = ((DesignatorBase)(
    				(((DesignatorIndex)
    						(factor.getDesignator().getDesignatorSpec()))
    						.getDesignator().getDesignatorSpec())
    				)).getName();
    	}
    	if(name=="") {
    		log.info("Function not found.");
    	}
		factor.getDesignator().obj = findVisibleSymbol(name);
		factor.struct = factor.getDesignator().obj.getType();
    }
    
    public void visit(DesignatorStatementFcall designator){
        designator.obj = designator.getDesignator().obj;
    }
    
    public void visit(Designator designator) {
    	designator.obj = designator.getDesignatorSpec().obj;
    }
    
	public Obj findVisibleSymbol(String name) {
		Obj symbol = null;
		Scope scope = Tab.currentScope();
		while(symbol==null && scope!=null) {
			symbol = scope.findSymbol(name);
			if(symbol==null) scope = scope.getOuter();
		}
		
		if(symbol==null) {
			log.info("Couldn't find symbol in scope"+name);
			if(Tab.currentScope().getLocals()!=null)
				symbol = Tab.currentScope().getLocals().searchKey(name);
			if(symbol==Tab.noObj || symbol==null) {
				log.info("Couldn't find symbol in locals"+name);
			}
		}
		
		return symbol;	
	}
	
	public Obj findCurrentScope(String name) {
		Obj symbol = null;

		if(Tab.currentScope() != null) {
			symbol = Tab.currentScope().findSymbol(name);
		}
		
		if(symbol==null) {
			// log.info("Couldn't find symbol in scope"+name);
			if(Tab.currentScope().getLocals()!=null)
				symbol = Tab.currentScope().getLocals().searchKey(name);
			if(symbol==Tab.noObj || symbol==null) {
				symbol = null;
				// log.info("Couldn't find symbol in locals"+name);
			}
		}
		return symbol;	
	}
	
	public boolean actParsExprCondition(SyntaxNode parent) {
		return parent instanceof ActualsPars
				| parent instanceof PrintStmt
				| parent instanceof DesignatorStatementAssign
				| parent instanceof CondFact
				| parent instanceof ReturnExpr
				| parent instanceof DesignatorIndex;
	}

    public void visit(ProgName progName) { 
    	progName.obj = Tab.insert(Obj.Prog, progName.getPName(), Tab.noType);
    	Tab.openScope();
    }
    
    public void visit(Program program) { 
    	if(findVisibleSymbol("main")==null) {
    		log.info("Main not defined");
    	}
    	Tab.chainLocalSymbols(program.getProgName().obj);
    	Tab.closeScope();
    }
    
}
