package rs.ac.bg.etf.pp1;

import com.sun.istack.internal.logging.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticPass extends VisitorAdaptor {
	
	Logger log = Logger.getLogger(getClass());
	int varDeclCount = 0;
	int printCallCount = 0;
	
	public int getPrintCallCount() {
		return printCallCount;
	}
	
	public void visit(VarDeclList varDeclList) {
		
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
		log.info(varDecl.toString("")+"\n\n"+declList.getType().toString("")+declList.getType().struct);
		log.info(varObj.getName() + " " + varObj.getType() + " " + varObj.getAdr());
		varDeclCount++;
	}
	
    public void visit(ProgName progName) { 
    	progName.obj = Tab.insert(Obj.Prog, progName.getPName(), Tab.noType);
    	Tab.openScope();
    }
    
    public void visit(Program program) { 
    	log.info("obj" + program.getProgName().obj);
    	Tab.chainLocalSymbols(program.getProgName().obj);
    	Tab.closeScope();
    }
}
