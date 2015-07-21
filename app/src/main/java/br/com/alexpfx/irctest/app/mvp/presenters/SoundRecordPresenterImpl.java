package br.com.alexpfx.irctest.app.mvp.presenters;

import br.com.alexpfx.irctest.app.mvp.model.domain.audio.RecorderUseCase;
import br.com.alexpfx.irctest.app.mvp.view.SoundRecordView;

/**
 * Created by alexandre on 21/07/15.
 */
public class SoundRecordPresenterImpl implements SoundRecordPresenter, RecorderUseCase.Listener{


    private RecorderUseCase recorderUseCase;
    private SoundRecordView soundRecordView;

    public SoundRecordPresenterImpl(SoundRecordView soundRecordView, RecorderUseCase recorderUseCase) {
        this.soundRecordView = soundRecordView;
        this.recorderUseCase = recorderUseCase;
    }


    @Override
    public void recordSound() {
        recorderUseCase.startRecord();
    }

    @Override
    public void stopRecordSound() {
        recorderUseCase.stopRecord();
    }

    @Override
    public void audioRecorded(byte[] buffer) {
        System.out.println(buffer);


    }
}
