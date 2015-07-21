package br.com.alexpfx.irctest.app.mvp.model.domain.audio;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import br.com.alexpfx.irctest.app.mvp.model.domain.executor.ThreadExecutor;

/**
 * Created by alexandre on 21/07/15.
 */
public class RecorderUseCaseImpl implements RecorderUseCase {

    private int sampleRate = 44100;
    private int channelConfiguration = AudioFormat.CHANNEL_CONFIGURATION_MONO;
    private int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;
    private int frameByteSize = 2048;
    ThreadExecutor threadExecutor = ThreadExecutor.ThreadExecutorSingleton.INSTANCE.get();
    private boolean isRecording = true;

    private AudioRecord audioRecord;
    private byte[] buffer;

    private Listener listener;

    public RecorderUseCaseImpl(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void init() {
        int recBufSize = AudioRecord.getMinBufferSize(sampleRate, channelConfiguration, audioEncoding);
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, sampleRate, channelConfiguration, audioEncoding, recBufSize);
        buffer = new byte[frameByteSize];
    }

    @Override
    public void startRecord() {
        init();
        isRecording = true;
        threadExecutor.run(this);

    }

    @Override
    public void stopRecord() {
        isRecording = false;
        threadExecutor.run(this);
    }

    @Override
    public void run() {
        if (isRecording) {
            audioRecord.startRecording();
        } else {
            analyzeSound();
            audioRecord.stop();
            audioRecord.release();
        }
    }

    private void analyzeSound() {
        System.out.println("analise");
        final int read = audioRecord.read(buffer, 0, frameByteSize);
        short sample = 0;
        int totalAbsValue = 0;
        float averageAbsValue = 0.0f;
        for (int i = 0; i < frameByteSize; i += 2) {
            sample = (short)((buffer[i]) | buffer[i + 1] << 8);
            totalAbsValue += Math.abs(sample);
        }
        averageAbsValue = totalAbsValue / frameByteSize / 2;
        // have input
        if (averageAbsValue > 30) {
            listener.audioRecorded(buffer);
        }
    }
}
