package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.SuperVillain;
import com.example.model.demoClass;
import com.example.repository.VillainDao;
/*
 * Steretypes are annotations that tell spring that the class they are above
 * will be a Spring Bean.
 * There is a hierarchy to these annotations. The grandparent is:
 * 		@Component
 * The various children are
 * 		@Service- service is used for your service classes
 * 		@Repository- repository is used for your repository/dao classes
 * 		@Controller- controller is used for your controller classes
 * 		@Configuration- configuration is used for classes that are created
 * 			as an alternative configuration method to the beans.xml file
 * 		@RestController- is the same as a controller BUT it assumes your API
 * 			will be a RESTful API.
 * 		, etc
 */
@Service("villServ009")
public class VillainServiceImpl implements VillainService {
	public static int counter= 0;
	
	//creating a DAO instance without DI, THIS IS THE OLD WAY OF DOING THINGS
	//private VillainDao villainDao = new VillainDaoImpl();

	//with DI
	@Autowired //using autowired above a property tells spring to attempt byName
				// and if that doesn't work byType
	private VillainDao villainDao;
	
	private demoClass demo;
	
	public demoClass getDemo() {
		return demo;
	}

	public void setDemo(demoClass demo) {
		System.out.println("in demo setter");
		this.demo = demo;
	}

	public VillainServiceImpl() {
		System.out.println("In no-arg(s) constructor");
		counter++;
	}
	
	//@Autowired //using autowired above a constructor tells spring to
			//	attempt constructor
	public VillainServiceImpl(demoClass demo) {
		System.out.println("In our halfDEMO constructor");
		this.demo = demo;
		counter++;
	}
	public VillainServiceImpl(VillainDao villainDao) {
		System.out.println("In 1 arg constructor");
		this.villainDao = villainDao;
		counter++;
	}
	public VillainServiceImpl(VillainDao villainDao, demoClass demo) {
		System.out.println("In our DEMO constructor");
		this.villainDao = villainDao;
		this.demo = demo;
		counter++;
	}
	//										0 index, 1 index
	public VillainServiceImpl(VillainDao villainDao, int rev) {
		System.out.println("In two args constructor "+rev);
		this.villainDao = villainDao;
		counter++;
	}
	
	//						0 index  , 1 index
	//@Autowired
	public VillainServiceImpl(int rev, VillainDao villainDao) {
		System.out.println("In ANOTHER two args constructor "+rev);
		this.villainDao = villainDao;
		counter++;
	}
	
	@Override
	public List<SuperVillain> getAllVills() {
		return villainDao.selectAll();
	}

	//OUR GETTER(S) AND SETTER(S)
	public VillainDao getVillainDao() {
		return villainDao;
	}

	@Autowired //using autowired above a setters tells spring to attempt byType
	// and if that doesn't work byName
	public void setVillainDao(VillainDao villainDao) {
		System.out.println("in setter");
		this.villainDao = villainDao;
	}

}
