package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {

       /*
        OutsourcedPart o= new OutsourcedPart();
        o.setCompanyName("Western Governors University");
        o.setName("out test");
        o.setInv(5);
        o.setPrice(20.0);
        o.setId(100L);
        outsourcedPartRepository.save(o);
        OutsourcedPart thePart=null;
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            if(part.getName().equals("out test"))thePart=part;
        }

        System.out.println(thePart.getCompanyName());
        */
        List<OutsourcedPart> outsourcedPartsList = (List<OutsourcedPart>) outsourcedPartRepository.findAll();
        if (partRepository.count() == 0) {
            if (outsourcedPartsList.isEmpty()) {

                OutsourcedPart engine = new OutsourcedPart();
                engine.setCompanyName("FusionStar");
                engine.setName("FusionStar Pulse Thruster Engine");
                engine.setInv(5);
                engine.setPrice(4000.0);
                engine.setId(11);
                engine.setMinInv(2);
                engine.setMinInv(10);
                outsourcedPartRepository.save(engine);

                OutsourcedPart nav = new OutsourcedPart();
                nav.setCompanyName("Fieldwell");
                nav.setName("Fieldwell Quantum Navigation Computer System");
                nav.setInv(5);
                nav.setPrice(2200.0);
                nav.setId(32);
                nav.setMinInv(2);
                nav.setMaxInv(10);
                outsourcedPartRepository.save(nav);

                OutsourcedPart core = new OutsourcedPart();
                core.setCompanyName("Schmidt & Muller");
                core.setName("Schmidt & Muller Fusion Reactor Core");
                core.setInv(5);
                core.setPrice(8500.0);
                core.setId(18);
                core.setMinInv(2);
                core.setMaxInv(10);
                outsourcedPartRepository.save(core);

                InhousePart shield = new InhousePart();
                shield.setName("AstroMech Electromagnetic Shield Generator");
                shield.setInv(5);
                shield.setPrice(6000.0);
                shield.setId(8);
                shield.setMinInv(2);
                shield.setMaxInv(10);
                partRepository.save(shield);

                InhousePart repair = new InhousePart();
                repair.setName("AstroMech Nanomachine Repair Kit");
                repair.setInv(5);
                repair.setPrice(2400.0);
                repair.setId(40);
                repair.setMinInv(2);
                repair.setMaxInv(10);
                partRepository.save(repair);
            }
        }


        List<OutsourcedPart> outsourcedParts =(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }

        /*
        Product bicycle= new Product("bicycle",100.0,15);
        Product unicycle= new Product("unicycle",100.0,15);
        productRepository.save(bicycle);
        productRepository.save(unicycle);
        */

    if (productRepository.count() == 0) {

        Product cv1000 = new Product("Celestial Luxury Cruiser CV-1000", 100000.0, 5);
        Product l2000 = new Product("CargoMaster Freighter L-2000", 230000.0, 5);
        Product ar900 = new Product("Renegade Repair Vessel AR-900", 45000.0, 5);
        Product om55 = new Product("Marlin-Five Ore Miner OM-500", 83000.0, 5);
        Product gb333 = new Product("DeepVoid Transport GB-333", 59000.0, 5);
        productRepository.save(cv1000);
        productRepository.save(l2000);
        productRepository.save(ar900);
        productRepository.save(om55);
        productRepository.save(gb333);
    }




        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}
