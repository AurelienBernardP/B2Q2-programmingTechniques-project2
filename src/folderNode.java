
public class folderNode extends nodeType{
    private String name;
    Node content;

    public folderNode(String name){
        this.name = name;
    }

    public void addNodeInFolder(nodeType newNode){
        if(newNode != null)
             content.addNode(newNode);
    }

    public nodeType createFile(String name, String content){
        fileNode newFile = new fileNode(name, content);
        addNodeInFolder(newFile);
        return newFile; //TYPE CAST OU PAS 
    }

    public nodeType createFolder(String name){
        folderNode newFolder = new folderNode(name);
        addNodeInFolder(newFolder);
        return newFolder;
        
    }
    public void copyNode(){

    }

    public nodeType createArchive(String name, String extension, int compressionLevel){
        nodeType newArchive = new archiveNode(name, extension, compressionLevel);
        addNodeInFolder(newArchive);
        return newArchive;
    }

    public nodeType createAlias(){
        return null;
    }
    
    public String getInfo(){
        return "yas folder";
    }
}