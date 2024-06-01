package com.example.truyenapp.view.adapter.admin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.admin.CommentDetailManagerActivity;
import com.example.truyenapp.R;
import com.example.truyenapp.api.CommentAPI;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.constraints.BundleConstraint;
import com.example.truyenapp.constraints.CommentState;
import com.example.truyenapp.model.Comment;
import com.example.truyenapp.request.CommentChangeRequest;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.utils.DialogEvent;
import com.example.truyenapp.utils.DialogHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentManagerAdapter extends RecyclerView.Adapter<CommentManagerAdapter.CommentManagerViewHolder> {
    private Context context;
    private List<Comment> list;

    public CommentManagerAdapter(Context context, List<Comment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CommentManagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_comment_management, parent, false);
        return new CommentManagerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentManagerViewHolder holder, int position) {
        Comment comment = list.get(position);
        if (comment == null) {
            return;
        }
        holder.setData(comment);
        holder.handleOnCLick();
    }


    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class CommentManagerViewHolder extends RecyclerView.ViewHolder implements DialogEvent {
        private TextView id, state, name, chapter;
        private Button btnAction;
        private Comment comment;
        private CommentState stateCanChange;
        private DialogHelper dialogHelper;

        public CommentManagerViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tv_comment_manager_id);
            state = itemView.findViewById(R.id.tv_comment_manager_state);
            name = itemView.findViewById(R.id.tv_comment_manager_name);
            chapter = itemView.findViewById(R.id.tv_comment_manager_chapter);
            btnAction = itemView.findViewById(R.id.btn_comment_manager_action);
            dialogHelper = new DialogHelper(itemView.getContext(), this);
        }

        private void setData(Comment comment) {
            this.comment = comment;
            id.setText("" + comment.getId());
            name.setText(comment.getBookName());
            String chapterNumber = comment.getChapterName().replace("Chapter", "").trim();
            chapter.setText(chapterNumber);
            btnAction.setTextColor(Color.WHITE);
            updateState();
        }

        private void handleOnCLick() {
            this.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, CommentDetailManagerActivity.class);
                intent.putExtra(BundleConstraint.ID_COMMENT, comment.getId());
                context.startActivity(intent);
            });
            this.btnAction.setOnClickListener(view -> {
                dialogHelper.showDialogCommentState(stateCanChange).show();
            });
        }

        private void changeState() {
            CommentChangeRequest request = new CommentChangeRequest(this.comment.getId(), stateCanChange.getState());
            Call<APIResponse> call = RetrofitClient.getInstance(itemView.getContext()).create(CommentAPI.class).changeState(request);
            call.enqueue(new Callback<APIResponse>() {
                @Override
                public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                    APIResponse apiResponse = response.body();
                    if (apiResponse.getCode() == 200) {
                        if (stateCanChange == CommentState.HIDE)
                            comment.setState(CommentState.HIDE.getState());
                        else comment.setState(CommentState.SHOW.getState());
                        updateState();
                        dialogHelper.showDialogChangeStateCommentSuccess().show();
                    }
                }

                @Override
                public void onFailure(Call<APIResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        }

        @Override
        public void onPositiveClick() {
            changeState();
        }

        @Override
        public void onNegativeClick() {

        }

        @Override
        public void onCancel() {

        }

        private void updateState() {
            if (this.comment.getState() == CommentState.HIDE.getState()) {
                state.setText("Bị khóa");
                btnAction.setText("Mở khóa");
                btnAction.setBackgroundColor(Color.GREEN);
                stateCanChange = CommentState.SHOW;
            } else {
                state.setText("Hoạt động");
                btnAction.setText("Khóa");
                btnAction.setBackgroundColor(Color.RED);
                stateCanChange = CommentState.HIDE;
            }
        }
    }
}
