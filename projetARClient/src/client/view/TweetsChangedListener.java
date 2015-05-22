package client.view;

import java.util.EventListener;

public interface TweetsChangedListener extends EventListener {
	public void tweetsChanged(TweetsChangedEvent event);
}
