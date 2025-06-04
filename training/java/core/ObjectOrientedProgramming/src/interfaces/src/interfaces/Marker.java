package interfaces;

public enum Marker  {
	Marker((LineMarkers)null);
	private LineMarkers lineMarker = null;
	private PointMarkers pointMarker = null;
	
	Marker(LineMarkers lineMarker)
	{
		this.lineMarker = lineMarker;
		
		
	}
	Marker(PointMarkers pointMarker)
	{
		this.pointMarker = pointMarker;
	}
	public LineMarkers getLineMarker() {
		return lineMarker;
	}
	public void setLineMarker(LineMarkers lineMarker) {
		this.lineMarker = lineMarker;
	}
	public PointMarkers getPointMarker() {
		return pointMarker;
	}
	public void setPointMarker(PointMarkers pointMarker) {
		this.pointMarker = pointMarker;
	}
	
	
	

}
