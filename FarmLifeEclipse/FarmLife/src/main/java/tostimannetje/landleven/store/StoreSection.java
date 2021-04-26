package tostimannetje.landleven.store;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import tostimannetje.landleven.Reference;
import tostimannetje.landleven.init.ModItems;
import tostimannetje.landleven.items.IHasPrice;
import tostimannetje.landleven.items.ItemSeed;

public abstract class StoreSection<T extends IHasPrice> {

	protected List<StoreItem> sectionItems = new ArrayList<StoreItem>();
	private int currentPage;
	private String name;
	private ResourceLocation icon;
	
	public StoreSection(String name, String icon) {
		this.name = name;
		this.icon = new ResourceLocation(Reference.MODID, icon);
	}
	
	public void addToStore(T item) {}
	
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
