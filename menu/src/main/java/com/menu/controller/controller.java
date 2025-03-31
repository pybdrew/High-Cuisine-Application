package com.menu.controller;

import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.menu.model.DrinkModel;
import com.menu.model.HomeModel;
import com.menu.model.LoginModel;
import com.menu.model.RegisterModel;
import com.menu.model.SandwichModel;

import jakarta.validation.Valid;



@Controller
public class controller
{
    /**
     * Mapping for homepage
     * @param model
     * @return
     */
    @GetMapping("/")
    public String home(Model model)
    {
        List<HomeModel> menus = new ArrayList<>();
        menus.add(new HomeModel("Drinks", "Warm up with our curated selection of hot and cold beverages.",
        "https://images.unsplash.com/photo-1509042239860-f550ce710b93?auto=format&fit=crop&w=800&q=80"));
        menus.add(new HomeModel("Sandwiches", "Explore our handcrafted sandwiches made with the freshesy ingredients.",
        "https://images.unsplash.com/photo-1586190848861-99aa4a171e90?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80"));

        model.addAttribute("menus", menus);
        model.addAttribute("title", "Home");
        model.addAttribute("content", "Hello, welcome to the homepage!");
        return "home";
    }

    /**
     * Mapping for login
     * @param model
     * @return
     */
    @GetMapping("/login")
    public String login(Model model)
    {
        model.addAttribute("loginModel", new LoginModel());
        return "login";
    }

    /**
     * Check fields for login
     * @param loginModel
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/doLogin")
    public String doLogin(@Valid LoginModel loginModel, BindingResult bindingResult, Model model)
    {
        System.out.println(String.format("Form with username of %s and Password of %s", loginModel.getUsername(), loginModel.getPassword()));
        if (bindingResult.hasErrors())
        {
            model.addAttribute("title", "Login Form");
            return "login";
        }
        // Redirect to homepage after successful login
        return "redirect:/";
    }

    /**
     * Mapping for register
     * @param model
     * @return
     */
    @GetMapping("/register")
    public String register(Model model)
    {
        model.addAttribute("title", "Registration");
        model.addAttribute("registerModel", new RegisterModel());
        return "register";
    }

    @PostMapping("/doRegister")
    public String doRegister(@Valid RegisterModel registerModel, BindingResult bindingResult, Model model)
    {
        System.out.println(String.format("Form with First name: %s, Last name: %s, Username: %s, and Password: %s", registerModel.getFirstName(), registerModel.getLastName(), registerModel.getUsername(), registerModel.getPassword()));
        if(bindingResult.hasErrors())
        {
            model.addAttribute("title", "Registration");
            return "register";
        }
        //Redirect home if registration succesful
        return "redirect:/";
    }
    

    /**
     * Mapping for drink menu
     * @return
     */
    @GetMapping("/drink")
    public String drinkMenu(Model model)
    {
        List<DrinkModel> drinks = new ArrayList<>();

        drinks.add(new DrinkModel("Cappuccinio","Rich espresso with steamed milk and foam",
        "https://images.unsplash.com/photo-1541167760496-1628856ab772?auto=format&fit=crop&w=800&q=80"));
        drinks.add(new DrinkModel("Iced Coffee", "The perfect chill in every sip. Served in a clear glass with ice.",
                "https://images.unsplash.com/photo-1461023058943-07fcbe16d735?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        drinks.add(new DrinkModel("Espresso", "Bold and intense—pure coffee energy in a cup.",
                "https://images.unsplash.com/photo-1583165278997-0250ea5d72e2?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        drinks.add(new DrinkModel("Mocha", "Chocolate meets espresso in this sweet classic.",
                "https://plus.unsplash.com/premium_photo-1668970851336-6c81cc888ba7?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        drinks.add(new DrinkModel("Flat White", "Smooth espresso with velvety steamed milk.",
                "https://images.unsplash.com/photo-1495474472287-4d71bcdd2085?auto=format&fit=crop&w=800&q=80"));
        drinks.add(new DrinkModel("Macchiato", "Espresso topped with a dollop of foam.",
                "https://images.unsplash.com/photo-1563731649913-fab41907b535?q=80&w=1960&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        drinks.add(new DrinkModel("Cold Brew", "Slow-steeped coffee served over ice.",
                "https://plus.unsplash.com/premium_photo-1671559020928-dde18021036f?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        drinks.add(new DrinkModel("Nitro Coffee", "Infused with nitrogen for a creamy experience.",
                "https://images.unsplash.com/photo-1649882453742-c93c481edb77?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        
        model.addAttribute("title", "Drink Menu");
        model.addAttribute("drinks", drinks);
        return "drinks";
    }

    /**
     * Mapping for food menu(currently sandwiches)
     * @return
     */
    @GetMapping("/sandwich")
    public String sandwichhMenu(Model model)
    {
        List<SandwichModel> sandwiches = new ArrayList<>();
        sandwiches.add(new SandwichModel("Turkey Club",
    "Smoked turkey with crispy bacon, lettuce, tomato, and mayo.",
    "https://plus.unsplash.com/premium_photo-1664472757995-3260cd26e477?q=80&w=1961&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        sandwiches.add(new SandwichModel("BLT",
    "Crispy bacon, lettuce, tomato, and a dash of black pepper mayo.",
    "https://images.unsplash.com/photo-1619096252214-ef06c45683e3?q=80&w=1925&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        sandwiches.add(new SandwichModel("Grilled Cheese",
    "Golden, melty cheddar cheese on toasted sourdough.",
    "https://images.unsplash.com/photo-1528736235302-52922df5c122?q=80&w=1908&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        sandwiches.add(new SandwichModel("Ham & Swiss",
    "Black forest ham and melted Swiss with mustard on rye.",
    "https://images.unsplash.com/photo-1481070414801-51fd732d7184?q=80&w=1924&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        sandwiches.add(new SandwichModel("Veggie Delight",
    "Fresh cucumbers, spinach, tomatoes, and hummus in a wrap.",
    "https://plus.unsplash.com/premium_photo-1671559021919-19d9610c8cad?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        sandwiches.add(new SandwichModel("Chicken Caesar Wrap",
    "Grilled chicken, romaine, parmesan, and Caesar dressing.",
    "https://plus.unsplash.com/premium_photo-1700677185925-81d4d3edc860?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        sandwiches.add(new SandwichModel("Tuna Melt",
    "Tuna salad with cheddar on toasted bread—grilled to perfection.",
    "https://www.seriouseats.com/thmb/h3BPsOL0qqqBPYN4aivT3xEAZG4=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/20240214-TunaMelt-SEA--Peyton-Beckworth-1f500549fa8f48499e436fa3c2a0ca0e.jpg"));
        sandwiches.add(new SandwichModel("Italian Sub",
    "Salami, pepperoni, provolone, and lettuce on a hoagie roll.",
    "https://food.fnr.sndimg.com/content/dam/images/food/fullset/2011/2/4/1/RX-FNM_030111-Weeknight-Dinners-025_s4x3.jpg.rend.hgtvcom.616.462.suffix/1382539864691.webp"));

        model.addAttribute("title", "Sandwich Menu");
        model.addAttribute("sandwiches", sandwiches);
        return "sandwiches";
    }
}

