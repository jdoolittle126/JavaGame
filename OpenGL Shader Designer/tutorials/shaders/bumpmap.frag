varying vec3 v;

uniform sampler2D TextureUnit0;
uniform sampler2D TextureUnit1;

void main(void)
{
	vec4 basecolor = texture2D(TextureUnit0, vec2(gl_TexCoord[0]));
	vec4 N = texture2D(TextureUnit1, vec2(gl_TexCoord[1]));
	vec3 L = normalize(gl_LightSource[0].position.xyz - v); 
	vec3 E = normalize(-v);
	vec3 R = normalize(-reflect(L,N));
	vec3 H = normalize(L+E);
	vec4 amb, spec, diff;
	
	
	amb = gl_FrontLightProduct[0].ambient * gl_FrontMaterial.ambient;
	
	spec = gl_FrontLightProduct[0].specular
	* pow(max(dot(R,E),0.0),0.1);
	
	diff = gl_FrontLightProduct[0].diffuse * max(dot(N,L), 0.0);
 
 	gl_FragColor = basecolor + spec + diff + amb;
	
}

 
 

 
	
