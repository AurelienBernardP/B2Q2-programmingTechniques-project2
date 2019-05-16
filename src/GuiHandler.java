
import java.util.*;
import java.lang.*;
import java.io.*;
import montefiore.ulg.ac.be.graphics.*;

public class GuiHandler implements ExplorerEventsHandler {

	private ExplorerSwingView esv;
	private String log = "";
	
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
		//perform the requested manipulation on selectedNode and see if it is allowed
		if((newAlias = ((NodeType)selectedNode).createAlias()) != null){
			//if it is, modify the JTree and add the new node to the parent folder
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
			//if it is not, show an error popup window
			esv.showPopupError("Error: cannot create alias for this type of element.\n");
			return;
		}
	}

	public void createArchiveEvent(Object selectedNode) {

		//get user input information to create the archive
		String name = esv.displayArchiveWindow1();
		String extension = esv.displayArchiveWindow2();
		int compressionLVL = esv.displayArchiveWindow3();

		//verify if all the data was successfully entered
		if(name == null || extension == null || compressionLVL < 0 || esv.isRootNodeSelected()){
			//if it is not, show an error popup window
			esv.showPopupError("Error: operation 'create archive' has been cancelled or the selected node is the root.\n");
			return;
		}

		//perform the requested manipulation on selectedNode and see if it is allowed
		NodeType newArchive;
		if((newArchive = ((NodeType)selectedNode).createArchive(name, extension, compressionLVL)) != null){
			//if it is, modify the JTree and add the new node to the parent folder
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
			//if it is not, show an error popup window
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
		//verify that the selected node is not the root node
		if (esv.isRootNodeSelected()){
			//if it is report the error with a popup window
			esv.showPopupError("Error: cannot do a copy of the root.");
			return;
		}
		//perform the requested manipulation on selectedNode and see if it is allowed
		NodeType newNode = ((NodeType)selectedNode).copyNode();
		if(newNode == null){
			//if it is not, show an error popup window
			esv.showPopupError("Error: copying element.\n");
			return;
		}
		//if it is, modify the JTree and add the new node to the parent folder	
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

		//get user input information to create the archive
		String[] propertiesFile = this.esv.fileMenuDialog();
		//verify if all the data was successfully entered
		if(propertiesFile == null && (propertiesFile[0] == null || propertiesFile[1] == null)){
			//if it is not, show an error popup window
			esv.showPopupError("Error: operation 'create file' has been cancelled. \n");
			return;
		}

		//perform the requested manipulation on selectedNode and see if it is allowed
		NodeType newFile;
		if((newFile = ((NodeType)selectedNode).createFile(propertiesFile[0], propertiesFile[1])) != null){
			//if it is, modify the JTree and add the new node to the selected folder	
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
			//if it is not, show an error popup window
			esv.showPopupError("Error: cannot create file inside this type of element.\n");
			return;
		}
	}

	public void createFolderEvent(Object selectedNode) {
		//get user input information to create the archive
		String nameFolder = this.esv.folderMenuDialog();
		//verify if all the data was successfully entered
		if(nameFolder == null){
			//if it is not, show an error popup window
			esv.showPopupError("Error: operation 'create folder' has been cancelled. \n");
			return;				
		}

		//perform the requested manipulation on selectedNode and see if it is allowed
		NodeType newFolder;
		if((newFolder = ((NodeType)selectedNode).createFolder(nameFolder)) != null){
			//if it is, modify the JTree and add the new node to the selected folder	
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
			//if it is not, show an error popup window
			esv.showPopupError("Error: cannot create folder inside this type of element.\n");
			return;
		}
	}

	public void doubleClickEvent(Object selectedNode) {
		//get text area manager, clear the text area and add the information about the selected node
		TextAreaManager jt = this.esv.getTextAreaManager();
		jt.clearAllText();
		jt.appendText(((NodeType)selectedNode).getInfo(0));

		//log the event
		Logger newLog = new DateSystem(new UserSystem(new OsSystem(new DoubleClickEvent(new EndLog()))));
		System.out.println(newLog.log());
		this.log = this.log + (new String(newLog.getLog()));
	}

	public void eventExit() {
		//print the complete events log in the specified file stream
		System.out.println("final log:");
		System.out.println(log);
	}
}
