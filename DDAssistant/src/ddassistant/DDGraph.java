package ddassistant;
import javafx.scene.Node;
import javafx.scene.layout.Region;

@SuppressWarnings("restriction")
public class DDGraph extends Region {
	private DDGraphPane ddGraphPane;
	public Region getRegion(DDWell well) {
		Region region = new Region();
		return region;
	}

	public DDGraph(DDGraphPane ddGraphPane, DDWell well) {
		super();
		this.ddGraphPane = ddGraphPane;
	}

}
