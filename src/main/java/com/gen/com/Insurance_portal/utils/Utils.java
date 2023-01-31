package com.gen.com.Insurance_portal.utils;


import org.springframework.data.domain.Sort;

import java.util.*;

public class Utils {
    private Utils() {
        throw new UnsupportedOperationException("Cannot instantiate a Util class");
    }

    public static String generateRandomUuid() {
        return UUID.randomUUID().toString();
    }

//    public static Set<ResponseMenuModel> getMenus(Set<Role> roles) {
//
//        Set<Menu> menus = new HashSet<>();
//        Set<Menu> childMenus = new HashSet<>();
//
//        for (Role role: roles) {
//            if (role.isActive()){
//                Set<Menu> roleMenus = role.getMenus();
//                menus = roleMenus.stream().filter(menu -> menu.isActive() && menu.getOrderIndex() == -1 && menu.getParentId() == 0).collect(Collectors.toSet());
//                childMenus = roleMenus.stream().filter(menu -> menu.isActive() && menu.getOrderIndex() != -1 && menu.getParentId() != 0).collect(Collectors.toSet());
//            }
//        }
//
//        Set<ResponseMenuModel> responseMenuModels = menus.stream().map(menu -> MenuMapper.INSTANCE.menuToMenuResponse(menu)).collect(Collectors.toSet());
//        Set<ResponseMenuModel> responseChilsMenuModels = childMenus.stream().map(menu -> MenuMapper.INSTANCE.menuToMenuResponse(menu)).collect(Collectors.toSet());
//
//
//
//        for (ResponseMenuModel menu : responseMenuModels) {
//            Set<ResponseMenuModel> menuSet = new HashSet<>();
//            for (ResponseMenuModel childMenu : responseChilsMenuModels) {
//                if (menu.getId().equals(childMenu.getParentId())) {
//                    menuSet.add(childMenu);
//                }
//            }
//            menu.setChilds(menuSet);
//        }
//        responseMenuModels = sortMenu(responseMenuModels);
//        for (ResponseMenuModel menu:responseMenuModels){
//            menu.setChilds(sortMenu(menu.getChilds()));
//        }
//
//        return responseMenuModels;
//    }
//
//    public static Set<ResponseMenuModel> sortMenu(Set<ResponseMenuModel> menus) {
//        return menus.stream().sorted(Comparator.comparing(ResponseMenuModel::getOrderIndex)).collect(Collectors.toCollection(LinkedHashSet::new));
//    }

    public static Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }
}
