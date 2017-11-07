varying vec3 v;
uniform sampler2D TextureUnit0;
uniform sampler2D TextureUnit1;
 
vec3 GetVec3FromTex(sampler2D unit, vec2 coords)
{
	return vec3(texture2D(unit,coords)).xyz;
}
vec4 GetVec4FromTex(sampler2D unit, vec2 coords)
{
	return vec4(texture2D(unit,coords));
}
vec3 CalcAttenuation(float d)
{
  	vec3 att = d * (gl_LightSource[0].quadraticAttenuation * d)
 	      + gl_LightSource[0].linearAttenuation + gl_LightSource[0].constantAttenuation;
 	      
	return att;
}
vec3 GetAmbient()
{
	return (gl_LightSource[0].ambient.xyz * gl_FrontMaterial.ambient.xyz);
}
// this one works like it should
vec3 GetDiffuse(vec3 N, vec3 L)
{
	vec3 diff = (gl_LightSource[0].diffuse.xyz * gl_FrontMaterial.diffuse.xyz);
	diff *= max(dot(N,L), 0.0);
	
	return diff;
}
vec3 GetSpecular(vec3 N, vec3 H)
{
	vec3 spec = gl_LightSource[0].specular.xyz * gl_FrontMaterial.specular;
	spec *= pow(max(dot(N,H),0.0),gl_FrontMaterial.shininess);
	
	return spec;
}
void main(void)
{
	vec3 basecolor, amb, spec, diff, gloss,att;
	vec3 N,E,L,R,H;
	vec3 view = normalize(-v);

	float d;
	
	d =  distance(gl_LightSource[0].position.xyz, v);
	
	//basecolor = texture2D(TextureUnit0, vec2(gl_TexCoord[0]));
	basecolor = GetVec3FromTex(TextureUnit0, gl_TexCoord[0].xy);
	N = normalize(GetVec3FromTex(TextureUnit1, gl_TexCoord[1]));
	
	E = normalize(-v);
	L = normalize(gl_LightSource[0].position.xyz - v); 
	//vec3 R = 2*(dot(N,L))  * N - L; ;
	R = normalize(-reflect(L,N));
	H = (L+view)/2;
 	
 	
 	att = CalcAttenuation(d);
 
 	//A = Al*Am
	amb = GetAmbient();
	
	// D = Dl*Dm*max(L•N, 0)
	diff = GetDiffuse(N,L);
	
	// S = Sl*Sm*max((R•V), 0)n
	// S = Sl*Sm*max((N•H), 0)n
	spec = GetSpecular(N,H);
	
	//I = A + att*(D + g*S) 
	//gloss
	
	gl_FragColor = vec4(basecolor + amb + att*(spec + diff),1);
	
}
