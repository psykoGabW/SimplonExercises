
class Food {

	public final static String[] ATTRIBUTE_NAMES = { "Name", "Category", "Energetic value (kcal)",
			"Protein rate (g/100g)", "Glucid rate (g/100g)", "Lipid rate (g/100g)" };
	
	private static final String FORMAT_TO_STRING = "[ Name : %s | Category : %s | Energetic value : %d(kcal) | Protein rate : %02d(g/100g) | Glucid rate : %02d(g/100g) | Lipid rate : %02d(g/100g) ]";
	
	private String name;
	private String category;
	private int energeticValue; // kcal
	private int proteinRate; // x g/100g  format : xxx.yy
	private int glucidRate; // x g/100g format : xxx.yy
	private int lipidRate;  // x g/100g format : xxx.yy
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getEnergeticValue() {
		return energeticValue;
	}
	public void setEnergeticValue(int energeticValue) {
		this.energeticValue = energeticValue;
	}
	public int getProteinRate() {
		return proteinRate;
	}
	public void setProteinRate(int proteinRate) {
		this.proteinRate = proteinRate;
	}
	public int getGlucidRate() {
		return glucidRate;
	}
	public void setGlucidRate(int glucidRate) {
		this.glucidRate = glucidRate;
	}
	public int getLipidRate() {
		return lipidRate;
	}
	public void setLipidRate(int lipidRate) {
		this.lipidRate = lipidRate;
	}
		
	public String toString() {
		return String.format(FORMAT_TO_STRING,
				name,
				category,
				energeticValue,
				proteinRate,
				glucidRate,
				lipidRate);
	}
	
}
