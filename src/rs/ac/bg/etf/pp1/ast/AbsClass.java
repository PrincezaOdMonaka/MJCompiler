// generated with ast extension for cup
// version 0.8
// 29/0/2020 16:43:10


package rs.ac.bg.etf.pp1.ast;

public class AbsClass extends AbsClassDecl {

    private String ClassName;
    private ExtendDecl ExtendDecl;
    private VarDeclList VarDeclList;
    private AbsClassMethodDeclList AbsClassMethodDeclList;

    public AbsClass (String ClassName, ExtendDecl ExtendDecl, VarDeclList VarDeclList, AbsClassMethodDeclList AbsClassMethodDeclList) {
        this.ClassName=ClassName;
        this.ExtendDecl=ExtendDecl;
        if(ExtendDecl!=null) ExtendDecl.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.AbsClassMethodDeclList=AbsClassMethodDeclList;
        if(AbsClassMethodDeclList!=null) AbsClassMethodDeclList.setParent(this);
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String ClassName) {
        this.ClassName=ClassName;
    }

    public ExtendDecl getExtendDecl() {
        return ExtendDecl;
    }

    public void setExtendDecl(ExtendDecl ExtendDecl) {
        this.ExtendDecl=ExtendDecl;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public AbsClassMethodDeclList getAbsClassMethodDeclList() {
        return AbsClassMethodDeclList;
    }

    public void setAbsClassMethodDeclList(AbsClassMethodDeclList AbsClassMethodDeclList) {
        this.AbsClassMethodDeclList=AbsClassMethodDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExtendDecl!=null) ExtendDecl.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(AbsClassMethodDeclList!=null) AbsClassMethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExtendDecl!=null) ExtendDecl.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(AbsClassMethodDeclList!=null) AbsClassMethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExtendDecl!=null) ExtendDecl.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(AbsClassMethodDeclList!=null) AbsClassMethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AbsClass(\n");

        buffer.append(" "+tab+ClassName);
        buffer.append("\n");

        if(ExtendDecl!=null)
            buffer.append(ExtendDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AbsClassMethodDeclList!=null)
            buffer.append(AbsClassMethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AbsClass]");
        return buffer.toString();
    }
}
