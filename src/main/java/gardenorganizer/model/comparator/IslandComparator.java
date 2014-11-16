package gardenorganizer.model.comparator;

import gardenorganizer.model.Island;

import java.util.Comparator;

public class IslandComparator implements Comparator<Island> {

    @Override
    public int compare(Island arg0, Island arg1) {
	return arg0.getIslandName().compareTo(arg1.getIslandName());
    }

}
