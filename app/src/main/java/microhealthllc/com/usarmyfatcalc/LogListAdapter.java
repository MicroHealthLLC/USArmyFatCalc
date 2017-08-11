package microhealthllc.com.usarmyfatcalc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dan on 3/20/17.
 */


public class LogListAdapter extends RecyclerView.Adapter<LogListAdapter.MyViewHolder> {

    private List<LogModel> logList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView bodyfat, waist,neck, datetime, height;


        public MyViewHolder(View view) {
            super(view);
            bodyfat = (TextView) view.findViewById(R.id.bodyfat_text);
           waist = (TextView) view.findViewById(R.id.waist_text);
            neck = (TextView) view.findViewById(R.id.neck_text) ;
            datetime = (TextView) view.findViewById(R.id.date_text);
           height =(TextView) view.findViewById(R.id.height_txt);
        }
    }


    public LogListAdapter(List<LogModel> moviesList) {
        this.logList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listone, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       LogModel movie = logList.get(position);
        holder.bodyfat.setText(movie.getBodyfat());
        holder.waist.setText(movie.getWaist());
        holder.height.setText(movie.getHeight());
        holder.neck.setText(movie.getNeck());
        holder.datetime.setText(movie.getDateTime());
    }

    @Override
    public int getItemCount() {
        return logList.size();
    }
}