public class Map {
	private String	axis;
	private byte	value;

	public Map(String axis, byte value) {
		this.axis = axis;
		this.value = value;
	}

	protected String getAxis() {
		return axis;
	}

	protected void setAxis(String axis) {
		this.axis = axis;
	}

	protected byte getValue() {
		return value;
	}

	protected void setValue(byte value) {
		this.value = value;
	}

}
