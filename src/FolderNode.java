
public class FolderNode extends NodeType{
    private Node content;

    public FolderNode(String name){
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
        FileNode newFile = new FileNode(name, content);
        addNodeInFolder(newFile);
        return newFile;
    }

    public NodeType createFolder(String name){
        FolderNode newFolder = new FolderNode(name);
        addNodeInFolder(newFolder);
        return newFolder;
        
    }
    public NodeType copyNode(){
        FolderNode newFolder = new FolderNode(new String(super.name + "(copy)"));
        Node currentNode = content;
        NodeType currentContent;
        while(currentNode != null){
            currentContent = currentNode.getContent();
            if(currentContent instanceof FolderNode){
                newFolder = new FolderNode(currentContent.getName());
                newFolder.copyNode();

            } 
            if(currentContent instanceof FileNode || currentContent instanceof ArchiveNode){
                newFolder.addNodeInFolder(currentContent.copyNode());
            }
            currentNode = currentNode.getNext();
        }
        return (NodeType) newFolder;

    }

    public NodeType createArchive(String name, String extension, int compressionLevel){
        NodeType newArchive = new ArchiveNode(name, extension, compressionLevel);
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
            if(currentContent instanceof FolderNode){

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