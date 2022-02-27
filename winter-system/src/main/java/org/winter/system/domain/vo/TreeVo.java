package org.winter.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.winter.system.service.dto.MenuDto;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class TreeVo {

    private Long id;

    private String label;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TreeVo> children;

    public static List<TreeVo> buildElTree(List<MenuDto> menus) {
        List<TreeVo> trees = new LinkedList<>();
        for (MenuDto menu : menus) {
            TreeVo tree = new TreeVo();
            tree.setId(menu.getId());
            tree.setLabel(menu.getTitle());
            List<MenuDto> children = menu.getChildren();
            if (children != null && children.size() > 0) {
                tree.setChildren(buildElTree(children));
            }
            trees.add(tree);
        }
        return trees;
    }

    public static List<TreeVo> buildTreeselect(List<MenuDto> menus) {
        List<TreeVo> trees = new LinkedList<>();
        TreeVo root = new TreeVo();
        root.setId(0L);
        root.setLabel("主类目");
        trees.add(root);
        root.setChildren(buildElTree(menus));
        return trees;
    }

}
