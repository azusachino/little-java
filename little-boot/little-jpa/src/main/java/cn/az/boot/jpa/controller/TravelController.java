package cn.az.boot.jpa.controller;

import cn.az.boot.jpa.entity.TravelInfo;
import cn.az.boot.jpa.service.TravelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("travel")
public class TravelController {

    @Resource
    TravelService travelService;

    private static final String REDIRECT_LINK = "redirect:/travel/list";

    @RequestMapping("/")
    public String index() {

        return REDIRECT_LINK;
    }

    @RequestMapping("/list")
    public String list(ModelMap map) {
        List<TravelInfo> list = travelService.getTravelList();
        map.addAttribute("travelList", list);
        return "travel/list";
    }

    @RequestMapping("/detail/{travelId}")
    public String detail(@PathVariable Integer travelId, ModelMap map) {

        TravelInfo travelInfo = travelService.findTravelById(travelId);
        map.addAttribute("travelInfo", travelInfo);

        return "/travel/detail";
    }

    @RequestMapping("/delete/{travelId}")
    public String delete(@PathVariable Integer travelId) {

        travelService.delete(travelId);
        return REDIRECT_LINK;
    }

}
