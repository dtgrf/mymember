package co.yixiang.modules.system.domain;

import lombok.Data;

import java.util.List;
@Data
public class radioPie {

        private List<String> column;

        private List<RadioData> radioList;

        @Data
        public static class RadioData {
            private String name;

            private Integer value;
        }


}
