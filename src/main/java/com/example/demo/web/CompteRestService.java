package com.example.demo.web;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.CompteRepository;
import com.example.demo.entities.Compte;


@Component
@Path("/Banque")
public class CompteRestService {
	@Autowired
	private CompteRepository compteRepository;
	
	@POST
	@Path("/comptes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(@RequestParam Compte compte) {
		
		compteRepository.save(compte);
		return Response
				.status(Response.Status.OK)
				.entity(compte)
				.build();
	}
	
	@GET
	@Path("/comptes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Compte> getComptes(){
		return compteRepository.findAll();
	}
	
	@GET
	@Path("/compte/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response getCompte(@PathParam(value = "code")Long code){
		Optional<Compte> cmp= compteRepository.findById(code);
		return Response
				.status(Response.Status.OK)
				.entity(cmp)
				.build();
	}

	@DELETE
	@Path("/compte/delete/{code}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam(value = "code") Long code) {
		compteRepository.deleteById(code);
		return Response
				.status(Response.Status.OK)
				.entity(compteRepository.findById(code))
				.build();
	}
	
	@PUT
	@Path("/compte/update/{code}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response Update(@PathParam(value = "code")Long id,@RequestParam Compte user) {
		Optional<Compte> cpold= Optional.of(compteRepository.findById(user.getCode())).orElseThrow(RuntimeException::new);
		compteRepository.save(user);
		
		return  Response
				.status(Response.Status.OK)
				.entity(user)
				.build();
	}
	@GET
	@Path("/comptes/conversion/{code}")
	public Response conversion(@PathParam(value = "code")Long code,Compte compte) {
		double eur=0.305331;
		double soldecmpt =compteRepository.findById(code).get().getSolde();
		double conversion=soldecmpt*eur;
		return Response
				.status(Response.Status.OK)
				.entity(conversion)
				.build();
	}

}