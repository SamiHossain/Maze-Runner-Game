
//module asn2 {
//module SetupPollTrackerView {
module CPSC233MazeGame {

	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires java.scripting;
	requires java.desktop;
	requires javafx.media;
	

	opens model to javafx.graphics, javafx.fxml;
	opens application to javafx.graphics, javafx.fxml;

}
