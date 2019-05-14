
import java.util.*;
import java.lang.*;
import java.io.*;

import montefiore.ulg.ac.be.graphics.*;

public class GuiHandler implements ExplorerEventsHandler {

	private ExplorerSwingView esv;
	
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

	public void createCopyEvent(Object selectedNode) {
		// TODO Auto-generated method stub
		
	}

	public void createFileEvent(Object selectedNode) {
		String[] propertiesFile = this.esv.fileMenuDialog();
		if(propertiesFile[0] == null || propertiesFile[1] == null){
			esv.showPopupError("Error: operation 'create file' has been cancelled. \n");
			return;
		}

		NodeType newFile;
		if((newFile = ((NodeType)selectedNode).createFile(propertiesFile[0], propertiesFile[1])) != null){
			try {
    	        this.esv.addNodeToSelectedNode(newFile);
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
		//Si détails error pas nécessaire go with an else queen
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
		jt.clearAllText();
		jt.appendText(((NodeType)selectedNode).getInfo(0));
	}

	public void eventExit() {
		// TODO Auto-generated method stub
	}
}
