

public abstract class Entitas {
	
	protected Aszteroida aszteroida;
	protected static String ID;
	
	public abstract void Furas();
	
	public abstract void Halal();
	
	public abstract void Felrobban();
	
	public abstract void Lep();
	
	public abstract void Banyaszat();
	
	public void SetAszteroida(Aszteroida a) {
		aszteroida = a;
	}
	
	public abstract boolean BazisEpit(Utmutato u);
	
	public void SetID(String id) {
		ID = id;
	}
	
	public String getID() {
		return ID;
	}
	
	public Aszteroida getAszteroida() {
		return aszteroida;
	}
}
