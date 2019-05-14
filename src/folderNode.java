
public class folderNode extends NodeType{
    private Node content;

    public folderNode(String name){
        super();
        this.name = name;
    }

    public void addNodeInFolder(NodeType newNode){
        if(newNode != null){
            if(content == null){
                this.content = new Node(newNode);
            }else{
                this.content.addNode(newNode);
            }
        }
    }

    public NodeType createFile(String name, String content){
        fileNode newFile = new fileNode(name, content);
        addNodeInFolder(newFile);
        return newFile;
    }

    public NodeType createFolder(String name){
        folderNode newFolder = new folderNode(name);
        addNodeInFolder(newFolder);
        return newFolder;
        
    }
    public NodeType copyNode(){

    }

    public NodeType createArchive(String name, String extension, int compressionLevel){
        NodeType newArchive = new archiveNode(name, extension, compressionLevel);
        return newArchive;
    }

    public NodeType createAlias(){
        return null;
    }
    
    public String getInfo(int nbIndentation){
        String folderContent = "";
        Node currentNode = content;
        NodeType currentContent;
        while(currentNode != null){
            currentContent = currentNode.getContent();
            for(int i = nbIndentation; i > 0; i--)
                folderContent = folderContent + "  ";
            if(nbIndentation != 0)
                folderContent = folderContent + "-";
            if(currentContent instanceof folderNode){

                folderContent = folderContent + " " + currentContent.getName() + "\n";
                folderContent = folderContent + currentContent.getInfo(nbIndentation + 1);
            }
            else
                folderContent = folderContent + " " + currentContent.getName() + "\n";
            currentNode = currentNode.getNext();
        }
        return folderContent;
    }
}