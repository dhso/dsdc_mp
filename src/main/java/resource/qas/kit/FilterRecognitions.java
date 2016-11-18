package resource.qas.kit;

import org.ansj.recognition.impl.FilterRecognition;

public class FilterRecognitions {
	// 预处理
	public static FilterRecognition preRecognition() {
		FilterRecognition fitler = new FilterRecognition();
		fitler.insertStopNatures("w");
		fitler.insertStopNatures("r");
		fitler.insertStopWord(" ");
		return fitler;
	}
}
