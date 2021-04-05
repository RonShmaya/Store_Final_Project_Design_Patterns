package Model;

public class Client implements ClientObserver {
	private String name;
	private String phoneNumber;
	private boolean isWantToGetUpdates;

	public Client(String name, String phoneNumber, boolean isWantToGetUpdates) throws Exception {
		setName(name);
		setPhoneNumber(phoneNumber);
		setWantToGetUpdates(isWantToGetUpdates);
	}

//getters and setters
	public String getName() {
		return name;
	}

	private void setName(String name) throws Exception {
		checkNameOk(name);
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	private void setPhoneNumber(String phoneNumber) throws Exception {
		checkPhoneNumberOk(phoneNumber);
		this.phoneNumber = phoneNumber;
	}

	public boolean isWantToGetUpdates() {
		return isWantToGetUpdates;
	}

	private void setWantToGetUpdates(boolean isWantToGetUpdates) {
		this.isWantToGetUpdates = isWantToGetUpdates;
	}

	private void checkNameOk(String name) throws Exception {
		if (name == null || name == "")
			throw new Exception("Customer name cannot be empty");
		for (int i = 0; i < name.length(); i++)
			if (Character.isDigit(name.charAt(i)))
				throw new Exception("Customer name cannot have digits");
	}

	private void checkPhoneNumberOk(String phoneNumber) throws Exception {
		int phoneLenght = 10;
		if (phoneNumber.length() != phoneLenght)
			throw new Exception("phoneNumber number have " + phoneLenght + " Digits");
		for (int i = 0; i < phoneNumber.length(); i++)
			if (!Character.isDigit(phoneNumber.charAt(i)))
				throw new Exception("phoneNumber number have Only Digits!");
	}

	public String toString() {
		return "Client --->\tName: " + this.name + "\tPhone Number: " + this.phoneNumber + "\tGet Updates: "
				+ this.isWantToGetUpdates;
	}

	@Override
	public void reciveMsg(Sender sender, String msg) { // client get Msg (if wants)
		sender.getRecive(this.name);

	}

}
