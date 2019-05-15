
import java.util.*;
import java.lang.*;
import java.io.*;
import montefiore.ulg.ac.be.graphics.*;

public class GuiHandler implements ExplorerEventsHandler {

	private ExplorerSwingView esv;
	private String log;
	
    GuiHandler(String[] args) throws NullHandlerException {
        this.esv = new ExplorerSwingView(this);
        
        try {
        	// First step to do before anything !!! 
            this.esv.setRootNode(new FolderNode("Root")); // set the root node with a silly "A" object
        } catch (RootAlreadySetException e) {
            e.printStackTrace();
        }
    }
	
	public void createAliasEvent(Object selectedNode) {
		NodeType newAlias;
		if((newAlias = ((NodeType)selectedNode).createAlias()) != null){
			try {
    	        FolderNode parent = (FolderNode)this.esv.addNodeToParentNode(newAlias);
				parent.addNodeInFolder(newAlias);
				Logger newLog = new DateSystem(new UserSystem(new OsSystem(new AliasEvent(new EndLog()))));
				newLog.log();
				System.out.println(newLog.getLog());
				this.log = this.log + (new String(newLog.getLog()));
				esv.refreshTree();
			} catch (NoSelectedNodeException e) {
				e.printStackTrace();
				esv.showPopupError("Error: creating alias.\n");
        	} catch(NoParentNodeException p){
				p.printStackTrace();
				esv.showPopupError("Error: the selected node has no parent.fg\n");
			}
		}
		else{
			esv.showPopupError("Error: cannot create alias for this type of element.\n");
			return;
		}
	}

	public void createArchiveEvent(Object selectedNode) {
		String name = esv.displayArchiveWindow1();
		String extension = esv.displayArchiveWindow2();
		int compressionLVL = esv.displayArchiveWindow3();
		if(name == null || extension == null || compressionLVL < 0 || esv.isRootNodeSelected()){
			esv.showPopupError("Error: operation 'create archive' has been cancelled or the selected node is the root.\n");
			return;
		}

		NodeType newArchive;
		if((newArchive = ((NodeType)selectedNode).createArchive(name, extension, compressionLVL)) != null){
			try {
    	        FolderNode parent = (FolderNode)this.esv.addNodeToParentNode(newArchive);
				parent.addNodeInFolder(newArchive);
				Logger newLog = new DateSystem(new UserSystem(new OsSystem(new ArchiveEvent(new EndLog()))));
				newLog.log();
				System.out.println(newLog.getLog());
				this.log = this.log + (new String(newLog.getLog()));
				esv.refreshTree();
			} catch (NoSelectedNodeException e) {
				e.printStackTrace();
				esv.showPopupError("Error: creating an archive.\n");
        	} catch(NoParentNodeException p){
				p.printStackTrace();
				esv.showPopupError("Error: the selected node has no parent.\n");
			}
		}
		else{
			esv.showPopupError("Error: cannot create archive of this type of element.\n");
			return;
		}
	}

	private void createCopyFolderInJTree(Object selectedNode, int level){
		Node currentNode = ((FolderNode)selectedNode).getContent();
		NodeType currentContent;
		while(currentNode != null){
			currentContent = currentNode.getContent();
			try{
				esv.addNodeToLastInsertedNode(currentContent, level);
			}
			catch(NoPreviousInsertedNodeException e){
				e.printStackTrace();
				esv.showPopupError("No previously inserted node.\n");
			}
			catch(LevelException l){
				l.printStackTrace();
				esv.showPopupError("Level exceoption error.\n");
			}
			if(currentContent instanceof FolderNode){
				this.createCopyFolderInJTree(currentContent, level + 1);
			}
			currentNode = currentNode.getNext();
		}

	}

	public void createCopyEvent(Object selectedNode){
		if (esv.isRootNodeSelected()){
			esv.showPopupError("Error: cannot do a copy of the root.");
			return;
		}

		NodeType newNode = ((NodeType)selectedNode).copyNode();
		if(newNode == null){
			esv.showPopupError("Error: copying element.\n");
			return;
		}	
		try {
			((FolderNode)(this.esv.addNodeToParentNode(newNode))).addNodeInFolder(newNode);
			if (selectedNode instanceof FolderNode){
				this.createCopyFolderInJTree(selectedNode, 1);
			}
			Logger newLog = new DateSystem(new UserSystem(new OsSystem(new CopyEvent(new EndLog()))));
			newLog.log();
			System.out.println(newLog.getLog());
			this.log = this.log + (new String(newLog.getLog()));
			esv.refreshTree();
		} catch (NoSelectedNodeException e) {
			e.printStackTrace();
			esv.showPopupError("Error: copying element.\n");
    	} catch(NoParentNodeException p){
			p.printStackTrace();
			esv.showPopupError("Error: the selected node has no parent.fg\n");
		}
	}


	public void createFileEvent(Object selectedNode) {
		String[] propertiesFile = this.esv.fileMenuDialog();
		if(propertiesFile == null && (propertiesFile[0] == null || propertiesFile[1] == null)){
			esv.showPopupError("Error: operation 'create file' has been cancelled. \n");
			return;
		}

		NodeType newFile;
		if((newFile = ((NodeType)selectedNode).createFile(propertiesFile[0], propertiesFile[1])) != null){
			try {
    	        this.esv.addNodeToSelectedNode(newFile);
				Logger newLog = new DateSystem(new UserSystem(new OsSystem(new FileEvent(new EndLog()))));
				newLog.log();
				System.out.print(newLog.getLog());
				this.log = this.log + (new String(newLog.getLog()));
				esv.refreshTree();
			} catch (NoSelectedNodeException e) {
            	e.printStackTrace();
				esv.showPopupError("Error: creating file.\n");
        	}
		}
		else{
			esv.showPopupError("Error: cannot create file inside this type of element.\n");
			return;
		}
	}

	public void createFolderEvent(Object selectedNode) {
		String nameFolder = this.esv.folderMenuDialog();
		if(nameFolder == null){
			esv.showPopupError("Error: operation 'create folder' has been cancelled. \n");
			return;				
		}

		NodeType newFolder;
		if((newFolder = ((NodeType)selectedNode).createFolder(nameFolder)) != null){
			try {
				this.esv.addNodeToSelectedNode(newFolder);
				
				Logger newLog = new DateSystem(new UserSystem(new OsSystem(new FolderEvent(new EndLog()))));
				System.out.print(newLog.log());
				this.log = this.log + (new String(newLog.getLog()));
				esv.refreshTree();
			} catch (NoSelectedNodeException e) {
            	e.printStackTrace();
				esv.showPopupError("Error: creating folder.\n");
        	}
		}
		else{
			esv.showPopupError("Error: cannot create folder inside this type of element.\n");
			return;
		}
	}

	public void doubleClickEvent(Object selectedNode) {
		TextAreaManager jt = this.esv.getTextAreaManager();
		Logger newLog = new DoubleClickEvent(new DoubleClickEvent(new EndLog()));
		System.out.println(newLog.log());
		this.log = this.log + (new String(newLog.getLog()));
		jt.clearAllText();
		jt.appendText(((NodeType)selectedNode).getInfo(0));
	}

	public void eventExit() {
		System.out.println("final log:");
		System.out.println(log);
	}
}
