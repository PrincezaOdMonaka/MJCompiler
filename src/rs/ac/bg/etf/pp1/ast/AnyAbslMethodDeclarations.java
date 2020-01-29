// generated with ast extension for cup
// version 0.8
// 29/0/2020 16:43:10


package rs.ac.bg.etf.pp1.ast;

public class AnyAbslMethodDeclarations extends AnyMethodDeclList {

    private AnyMethodDeclList AnyMethodDeclList;
    private AbsMethodDecl AbsMethodDecl;

    public AnyAbslMethodDeclarations (AnyMethodDeclList AnyMethodDeclList, AbsMethodDecl AbsMethodDecl) {
        this.AnyMethodDeclList=AnyMethodDeclList;
        if(AnyMethodDeclList!=null) AnyMethodDeclList.setParent(this);
        this.AbsMethodDecl=AbsMethodDecl;
        if(AbsMethodDecl!=null) AbsMethodDecl.setParent(this);
    }

    public AnyMethodDeclList getAnyMethodDeclList() {
        return AnyMethodDeclList;
    }

    public void setAnyMethodDeclList(AnyMethodDeclList AnyMethodDeclList) {
        this.AnyMethodDeclList=AnyMethodDeclList;
    }

    public AbsMethodDecl getAbsMethodDecl() {
        return AbsMethodDecl;
    }

    public void setAbsMethodDecl(AbsMethodDecl AbsMethodDecl) {
        this.AbsMethodDecl=AbsMethodDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AnyMethodDeclList!=null) AnyMethodDeclList.accept(visitor);
        if(AbsMethodDecl!=null) AbsMethodDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AnyMethodDeclList!=null) AnyMethodDeclList.traverseTopDown(visitor);
        if(AbsMethodDecl!=null) AbsMethodDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AnyMethodDeclList!=null) AnyMethodDeclList.traverseBottomUp(visitor);
        if(AbsMethodDecl!=null) AbsMethodDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AnyAbslMethodDeclarations(\n");

        if(AnyMethodDeclList!=null)
            buffer.append(AnyMethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AbsMethodDecl!=null)
            buffer.append(AbsMethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AnyAbslMethodDeclarations]");
        return buffer.toString();
    }
}
