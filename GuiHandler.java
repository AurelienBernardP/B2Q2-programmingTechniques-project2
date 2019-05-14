import montefiore.ulg.ac.be.graphics.*;

public class GuiHandler implements ExplorerEventsHandler {

	private ExplorerSwingView esv;
	
    GuiHandler(String[] args) throws NullHandlerException {
        this.esv = new ExplorerSwingView(this);
        
        try {
        	// First step to do before anything !!! 
            this.esv.setRootNode(new A("Hey")); // set the root node with a silly "A" object
        } catch (RootAlreadySetException e) {
            e.printStackTrace();
        }
    }
	
	@Override
	public void createAliasEvent(Object selectedNode) {
		// TODO Auto-generated method stub
	}

	@Override
	public void createArchiveEvent(Object selectedNode) {
		// TODO Auto-generated method stub
	}

	@Override
	public void createCopyEvent(Object selectedNode) {
		// TODO Auto-generated method stub
	}



	//A quel point doit on respecter le design pattern ? 
	//Doit on définir chaque opération pour chaque classe 
	//(les opérations de Product & Creator)
	@Override
	public void createFileEvent(Object selectedNode) {
		if(selectedNode instanceof folderNode){ //maybe add condition root
			selectedNode.createFile();
			refreshTree();
		}
		else
			showPopupError("BITCH NO \n");
	}

	@Override
	public void createFolderEvent(Object selectedNode) {
		// TODO Auto-generated method stub
	}

	@Override
	public void doubleClickEvent(Object selectedNode) {
		// TODO Auto-generated method stub
	}

	@Override
	public void eventExit() {
		// TODO Auto-generated method stub
	}
}
