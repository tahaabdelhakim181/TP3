package ma.mundia.bdccprojectspringmvc.web;

import jakarta.validation.Valid;
import ma.mundia.bdccprojectspringmvc.Entites.Product;
import ma.mundia.bdccprojectspringmvc.respostory.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class productControlleur {
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("")
    public String home(){
        return "redirect:/user/index";
    }

    @GetMapping("/user/index")
    @PreAuthorize("hasRole('USER')")
    public String index(Model model){
        List<Product> produit= productRepository.findAll();
        model.addAttribute("produitList",produit);
        return "product";
    }
    @PostMapping("/admin/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@RequestParam(name="id") Long id){
     productRepository.deleteById(id);
     return "redirect:/user/index";
    }
    @GetMapping("/access_denied")
    public String accessDenied(){
        return "accessdenied";
    }
    @GetMapping("/admin/createproduit")
    @PreAuthorize("hasRole('ADMIN')")
    public String CreateProduit(Model model){
        model.addAttribute("product", new Product());
        return "Createproduit";
    }
    @GetMapping("/login")
    public String login_page(){
        return "login";
    }
    @PostMapping("/admin/saveproduit")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveproduit(@Valid Product product, BindingResult result,Model model){
       if(result.hasErrors()){
           System.out.println(result.getAllErrors());
           System.out.println("ee"+product);

           model.addAttribute("product",product);
           return "Createproduit";
       }
       productRepository.save(product);
       return "redirect:/user/index";
    }
}
