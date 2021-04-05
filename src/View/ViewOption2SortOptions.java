package View;

import Commands.SendSortPickCommand;
import Model.MyMap.eSortPick;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class ViewOption2SortOptions extends ViewOption {
	private RadioButton[] btn;
	private ToggleGroup tGroup;

	public ViewOption2SortOptions(VBox placeView, String title, MainView myMainView) {
		super(placeView, title, myMainView);
		btn = new RadioButton[3];
		btn[0] = new RadioButton("Sort Product By Alphabetic (ascending order)");
		btn[1] = new RadioButton("Sort Product By Alphabetic (Descending order)");
		btn[2] = new RadioButton("Sort Product By Entry order");
		tGroup = new ToggleGroup();
		for (int i = 0; i < btn.length; i++) {
			btn[i].setToggleGroup(tGroup);
			this.property.add(btn[i], 0, i);
		}
		this.property.add(this.btnAdd, 0, 3);
		this.property.add(this.msgUser, 0, 4);
		this.btnAdd.setOnAction(e -> {
			SendSortPickCommand cmd = new SendSortPickCommand(this);
			cmd.execute();
		});

	}

	public void mainViewUpdateModelPickSort(eSortPick pick) {
		this.myMainView.viewUpdateModelSortedPick(pick);
	}

	public RadioButton[] getBtn() {
		return btn;
	}

	public ToggleGroup gettGroup() {
		return tGroup;
	}

}
