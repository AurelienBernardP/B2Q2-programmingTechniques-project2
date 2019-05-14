
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

    public nodeType createAlias(){
        return new aliasNode(this.name, this.content);
    }
    
    public nodeType createFile(String name, String content){
        return null;
        //showPopupError("Cannot create a file inside a file. \n");
    }

    public nodeType createFolder(String name){
        return null;
        //showPopupError("Cannot create a folder inside a file. \n");
    }

    public nodeType createArchive(String name, String extension, int compressionLevel){
        return null;
        //showPopupError("Cannot archive a file. \n");
    }

    public void copyNode(){

    }
    public String getInfo(){
        return "yaaas file";
    }
}