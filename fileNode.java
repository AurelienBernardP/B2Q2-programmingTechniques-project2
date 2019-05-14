import montefiore.ulg.ac.be.graphics.*;

public class fileNode extends nodeType{
    private final String content;
    private final String name;

    public fileNode(String name, String content){
        this.content = content;
        this.name = name;
    }
    public String getContent(){
        return this.content;
    }

}