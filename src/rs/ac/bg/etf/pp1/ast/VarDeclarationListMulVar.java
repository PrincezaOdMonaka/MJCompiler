// generated with ast extension for cup
// version 0.8
// 29/0/2020 16:43:9


package rs.ac.bg.etf.pp1.ast;

public class VarDeclarationListMulVar extends VarDeclListMul {

    private VarDeclListMul VarDeclListMul;
    private VarDeclList VarDeclList;

    public VarDeclarationListMulVar (VarDeclListMul VarDeclListMul, VarDeclList VarDeclList) {
        this.VarDeclListMul=VarDeclListMul;
        if(VarDeclListMul!=null) VarDeclListMul.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
    }

    public VarDeclListMul getVarDeclListMul() {
        return VarDeclListMul;
    }

    public void setVarDeclListMul(VarDeclListMul VarDeclListMul) {
        this.VarDeclListMul=VarDeclListMul;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclListMul!=null) VarDeclListMul.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclListMul!=null) VarDeclListMul.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclListMul!=null) VarDeclListMul.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclarationListMulVar(\n");

        if(VarDeclListMul!=null)
            buffer.append(VarDeclListMul.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclarationListMulVar]");
        return buffer.toString();
    }
}
