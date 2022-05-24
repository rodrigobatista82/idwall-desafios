package idwall.desafio.string;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public abstract class StringFormatter {

	protected Integer limit;
	protected boolean justify;

	protected StringFormatter() {
		this.limit = 40;
		this.justify = false;
	}

	protected StringFormatter(Integer limit, boolean justify) {
		this.limit = limit;
		this.justify = justify;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public boolean isJustify() {
		return justify;
	}

	public void setJustify(boolean justify) {
		this.justify = justify;
	}

	/**
	 * It receives a text and should return it formatted
	 *
	 * @param text
	 * @return
	 */
	public abstract String format(String text);
}
