uniform sampler2D TextureUnit0;
uniform sampler2D TextureUnit1;
uniform sampler2D TextureUnit2;

uniform float scale;
uniform float bias;

void main(void)
{
	vec4 light0color  = gl_LightSource[0].diffuse;
	vec4 light1color  = gl_LightSource[1].diffuse;
	vec4 ambient = gl_LightModel.ambient;

	vec3 rgb, normal, temp, bump, total, eyevects;
	vec3 light0tsvec,light1tsvec,height;
	vec4 newtexcoord;
	
	eyevects = normalize(gl_TexCoord[3]).xyz;
	
	height = texture2D(TextureUnit2, gl_TexCoord[0]);
	
	height = height * scale + bias;
	newtexcoord.xyz = height * eyevects - gl_TexCoord[0].xyz;
	
	rgb = texture2D(TextureUnit0, newtexcoord).xyz;
	normal = texture2D(TextureUnit1, newtexcoord).xyz;

	normal = (normal - 0.5) * 2;
	normal = normalize(normal);

	light0tsvec = normalize(gl_TexCoord[1]).xyz;
	bump = dot(normal, light0tsvec);
	
	
 	total = saturate(bump * light0color);

	light1tsvec = normalize(gl_TexCoord[2]).xyz;
 	bump = dot(normal, light1tsvec);

	bump = clamp(light1color.rgb * bump.x, 0.0, 1.0);
	
	bump = saturate(bump * light1color);
	total = saturate(total + bump);

	total = saturate(total + ambient);
	
 	gl_FragColor = vec4(saturate(rgb * total),1);
 	
}
