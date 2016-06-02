package com.hhl.flowlayoutdemo.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Luozhimin on 2016/6/2.16:37
 */
public class CommentListEntity implements Serializable {


    /**
     * succeed : 1
     */

    private StatusEntity status;
    /**
     * total : 36
     * count : 10
     * more : 1
     */

    private PaginatedEntity paginated;
    /**
     * cmt_id : 6212
     * parent_id : 0
     * cmt_rank : 5
     * content : 一群小鲜肉们~
     * floor : 1
     * digg_count : 0
     * is_digg : 0
     * add_time : 2016-02-24 09:23:30
     * user : {"id":"37506","name":"pikapika........Qiu","avatar":"http://www.aiderizhi.com/data/avatar/4e56daf8abebce1c_s.jpg"}
     */

    private List<DataEntity> data;

    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    public void setPaginated(PaginatedEntity paginated) {
        this.paginated = paginated;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public PaginatedEntity getPaginated() {
        return paginated;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class StatusEntity implements Serializable{
        private int succeed;

        public void setSucceed(int succeed) {
            this.succeed = succeed;
        }

        public int getSucceed() {
            return succeed;
        }
    }

    public static class PaginatedEntity implements Serializable {
        private String total;
        private int count;
        private int more;

        public void setTotal(String total) {
            this.total = total;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public void setMore(int more) {
            this.more = more;
        }

        public String getTotal() {
            return total;
        }

        public int getCount() {
            return count;
        }

        public int getMore() {
            return more;
        }
    }

    public static class DataEntity  implements Serializable{
        private String cmt_id;
        private String parent_id;
        private String cmt_rank;
        private String content;
        private int floor;
        private String digg_count;
        private String is_digg;
        private String add_time;
        /**
         * id : 37506
         * name : pikapika........Qiu
         * avatar : http://www.aiderizhi.com/data/avatar/4e56daf8abebce1c_s.jpg
         */

        private UserEntity user;

        public void setCmt_id(String cmt_id) {
            this.cmt_id = cmt_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public void setCmt_rank(String cmt_rank) {
            this.cmt_rank = cmt_rank;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setFloor(int floor) {
            this.floor = floor;
        }

        public void setDigg_count(String digg_count) {
            this.digg_count = digg_count;
        }

        public void setIs_digg(String is_digg) {
            this.is_digg = is_digg;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public void setUser(UserEntity user) {
            this.user = user;
        }

        public String getCmt_id() {
            return cmt_id;
        }

        public String getParent_id() {
            return parent_id;
        }

        public String getCmt_rank() {
            return cmt_rank;
        }

        public String getContent() {
            return content;
        }

        public int getFloor() {
            return floor;
        }

        public String getDigg_count() {
            return digg_count;
        }

        public String getIs_digg() {
            return is_digg;
        }

        public String getAdd_time() {
            return add_time;
        }

        public UserEntity getUser() {
            return user;
        }

        public static class UserEntity implements Serializable {
            private String id;
            private String name;
            private String avatar;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getAvatar() {
                return avatar;
            }
        }
    }
}
