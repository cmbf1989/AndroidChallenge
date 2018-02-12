package frontkom.androidchallenge.Interfaces;

/**
 * Created by Cesar Ferreira on 09/02/2018.
 */

import android.view.View;

public interface RecyclerViewClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
