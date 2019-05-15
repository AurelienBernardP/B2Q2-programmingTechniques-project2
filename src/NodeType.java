

abstract class NodeType{
    protected String name;

    public String getName(){
        return name;
    }

    public void setName(String name) {
		this.name = name;
	}

    public abstract NodeType createFile(String name, String content);
    public abstract NodeType createFolder(String name);
    public abstract NodeType createArchive(String name, String extension, int compressionLevel);
    public abstract NodeType createAlias();
    public abstract String getInfo(int nbIdentation);
    public abstract NodeType copyNode();
    @Override 
    public String toString(){
        return name;
    }

}
