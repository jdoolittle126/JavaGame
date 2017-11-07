varying vec3 v;
varying vec3 N;
uniform sampler2D TextureUnit0;
 
void main(void)
{
	vec3 L = normalize(gl_LightSource[0].position.xyz - v); 
	vec3 E = normalize(-v);
	vec3 R = normalize(-reflect(L,N));
 	vec4 amb, spec, diff;
	vec4 basecolor = texture2D(TextureUnit0, vec2(gl_TexCoord[0]));
 
	amb = gl_FrontLightProduct[0].ambient * gl_FrontMaterial.ambient;
	
	
	//  Ks * (R dot V)^n

	spec = gl_FrontLightProduct[0].specular
	* pow(max(dot(R,E),0.0),0.1);
	
	//0.3*gl_FrontMaterial.shininess
	// Kd * (N dot L) +
	// completed
	diff = gl_FrontLightProduct[0].diffuse * max(dot(N,L), 0.0);
 
	gl_FragColor = (basecolor) + amb + spec + diff;
}
