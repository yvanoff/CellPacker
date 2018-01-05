package dev.lb.cellpacker.structure;

import java.util.List;
import java.util.Map;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import dev.lb.cellpacker.structure.view.SingleResourceView;
import dev.lb.cellpacker.structure.view.StaticResourceView;

public class SearchableResourceViewManager extends ResourceViewManager{

	private String searchString;
	
	public SearchableResourceViewManager(ResourceFile res) {
		super(res);
		setSearchString("");
	}

	public String getSearchString() {
		return searchString;
	}

	public boolean setSearchString(String searchString) {
		String old = this.searchString;
		this.searchString = searchString;
		return !old.equals(searchString);
	}
	
	public void setSearchString(String searchString, JTree toUpdate){
		if(setSearchString(searchString)){
			this.setTree(toUpdate);
		}
	}

	@Override
	public TreeNode createTree() {
		DefaultMutableTreeNode root = 
				new DefaultMutableTreeNode(new StaticResourceView("res.pak", "Resource file root node"));
		for(Map.Entry<String, List<SingleResourceView>> cat : views.entrySet()){
			DefaultMutableTreeNode catNode =
					new DefaultMutableTreeNode(new StaticResourceView(cat.getKey(), "Category root node"));
			for(SingleResourceView rv : cat.getValue()){
				if(rv.getName().contains(searchString))
					catNode.add(new DefaultMutableTreeNode(rv));
			}
			root.add(catNode);
		}
		return root;
	}
	

}
