package kr.hs.dgsw.realmemoapplication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Memo {
    private Long id;
    private String title;
    private String content;
    private Long updated;

    public Memo(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.updated = System.currentTimeMillis();
    }

    public Memo(String title, String content) {
        this.title = title;
        this.content = content;
        this.updated = System.currentTimeMillis();
    }

    public Memo(String title, String content, Long updated) {
        this.title = title;
        this.content = content;
        this.updated = updated;
    }
}
