package tostimannetje.landleven.store;

import java.util.ArrayList;
import java.util.List;


public abstract class StoreSection {

	protected List<StoreItem> sectionItems = new ArrayList<StoreItem>();
	private String name;
	
	public StoreSection(String name) {
		this.name = name;
	}
	
	public void addToStore(StoreItem item) {}
	
	public List<StoreItem> getSection() {
		return sectionItems;
	}
	
	public int getSectionPageCount() {
		return (int)Math.ceil(sectionItems.size() / 12.0);
	}
	
	public String getSectionName() {
		return this.name;
	}
}
