package cn.xlink.common.http.restful.xlink.mode;

import java.util.Arrays;
import java.util.List;

/**
 * xlink restful sql query class
 *
 * @author sswukang on 2016/11/15 15:12
 * @version 1.0
 */
public interface XLinkQuery {

    class In<T> implements XLinkQuery {
        public List<T> $in;

        public In(List<T> values) {
            this.$in = values;
        }

        public In(T... values) {
            this.$in = Arrays.asList(values);
        }
    }

    class Less<T> implements XLinkQuery {
        public T $lt;

        public Less(T value) {
            this.$lt = value;
        }
    }

    class LessAndEqual<T> implements XLinkQuery {
        public T $lte;

        public LessAndEqual(T value) {
            this.$lte = value;
        }
    }

    class Greater<T> implements XLinkQuery {
        public T $gt;

        public Greater(T value) {
            this.$gt = value;
        }
    }

    class GreaterAndEqual<T> implements XLinkQuery {
        public T $gte;

        public GreaterAndEqual(T value) {
            this.$gte = value;
        }
    }

    class Regex<T> implements XLinkQuery{
        public T $regex;

        public Regex(T value) {
            this.$regex = value;
        }
    }

}
