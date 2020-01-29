// generated with ast extension for cup
// version 0.8
// 29/0/2020 16:43:9


package rs.ac.bg.etf.pp1.ast;

public class DeclarationListAbs extends DeclList {

    private DeclList DeclList;
    private AbsClassDecl AbsClassDecl;

    public DeclarationListAbs (DeclList DeclList, AbsClassDecl AbsClassDecl) {
        this.DeclList=DeclList;
        if(DeclList!=null) DeclList.setParent(this);
        this.AbsClassDecl=AbsClassDecl;
        if(AbsClassDecl!=null) AbsClassDecl.setParent(this);
    }

    public DeclList getDeclList() {
        return DeclList;
    }

    public void setDeclList(DeclList DeclList) {
        this.DeclList=DeclList;
    }

    public AbsClassDecl getAbsClassDecl() {
        return AbsClassDecl;
    }

    public void setAbsClassDecl(AbsClassDecl AbsClassDecl) {
        this.AbsClassDecl=AbsClassDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DeclList!=null) DeclList.accept(visitor);
        if(AbsClassDecl!=null) AbsClassDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DeclList!=null) DeclList.traverseTopDown(visitor);
        if(AbsClassDecl!=null) AbsClassDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DeclList!=null) DeclList.traverseBottomUp(visitor);
        if(AbsClassDecl!=null) AbsClassDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DeclarationListAbs(\n");

        if(DeclList!=null)
            buffer.append(DeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AbsClassDecl!=null)
            buffer.append(AbsClassDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DeclarationListAbs]");
        return buffer.toString();
    }
}
