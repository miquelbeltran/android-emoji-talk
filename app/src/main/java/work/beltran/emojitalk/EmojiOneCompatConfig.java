package work.beltran.emojitalk;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.text.emoji.EmojiCompat;
import android.support.text.emoji.MetadataRepo;

public class EmojiOneCompatConfig extends EmojiCompat.Config {

    /**
     * Default constructor.
     *
     * @param context Context instance
     */
    public EmojiOneCompatConfig(@NonNull Context context) {
        super(new BundledMetadataLoader(context));
    }

    private static class BundledMetadataLoader implements EmojiCompat.MetadataRepoLoader {
        private final Context mContext;

        private BundledMetadataLoader(@NonNull Context context) {
            mContext = context.getApplicationContext();
        }

        @Override
        @RequiresApi(19)
        public void load(@NonNull EmojiCompat.MetadataRepoLoaderCallback loaderCallback) {
            final InitRunnable runnable = new InitRunnable(mContext, loaderCallback);
            final Thread thread = new Thread(runnable);
            thread.setDaemon(false);
            thread.start();
        }
    }

    @RequiresApi(19)
    private static class InitRunnable implements Runnable {
        private static final String FONT_NAME = "fonts/emojione.ttf";
        private final EmojiCompat.MetadataRepoLoaderCallback mLoaderCallback;
        private final Context mContext;

        private InitRunnable(final Context context,
                             final EmojiCompat.MetadataRepoLoaderCallback loaderCallback) {
            mContext = context;
            mLoaderCallback = loaderCallback;
        }

        @Override
        public void run() {
            try {
                final AssetManager assetManager = mContext.getAssets();
                final MetadataRepo resourceIndex = MetadataRepo.create(assetManager, FONT_NAME);
                mLoaderCallback.onLoaded(resourceIndex);
            } catch (Throwable t) {
                mLoaderCallback.onFailed(t);
            }
        }
    }
}
